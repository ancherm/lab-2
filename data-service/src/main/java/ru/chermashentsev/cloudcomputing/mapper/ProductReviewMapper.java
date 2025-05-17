package ru.chermashentsev.cloudcomputing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.chermashentsev.cloudcomputing.dto.request.product.review.CreateProductReviewRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.response.product.review.ProductReviewResponseDTO;
import ru.chermashentsev.cloudcomputing.entity.ProductReview;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductReviewMapper {

    ProductReview toModel(CreateProductReviewRequestDTO productReviewRequestDTO);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.description", target = "description")
    ProductReviewResponseDTO toDto(ProductReview productReview);

}
