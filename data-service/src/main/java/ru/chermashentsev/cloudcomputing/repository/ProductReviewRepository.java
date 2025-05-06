package ru.chermashentsev.cloudcomputing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chermashentsev.cloudcomputing.entity.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, String> {
}
