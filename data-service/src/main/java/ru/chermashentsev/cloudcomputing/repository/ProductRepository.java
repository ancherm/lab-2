package ru.chermashentsev.cloudcomputing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.chermashentsev.cloudcomputing.dto.response.report.AvgRatingAndCountReviewDTO;
import ru.chermashentsev.cloudcomputing.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
              select new ru.chermashentsev.cloudcomputing.dto.response.report.AvgRatingAndCountReviewDTO(
                  p.id,
                  p.description,
                  avg(r.rating),
                  count(r)
              )
              from ProductReview r
              join r.product p
              group by p.id
            """)
    List<AvgRatingAndCountReviewDTO> findProductRatingReport();

}
