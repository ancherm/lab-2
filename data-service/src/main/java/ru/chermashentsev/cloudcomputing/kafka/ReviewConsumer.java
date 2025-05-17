package ru.chermashentsev.cloudcomputing.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.chermashentsev.cloudcomputing.dto.request.product.CreateProductRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.request.product.review.CreateProductReviewRequestDTO;
import ru.chermashentsev.cloudcomputing.entity.Product;
import ru.chermashentsev.cloudcomputing.entity.ProductReview;
import ru.chermashentsev.cloudcomputing.entity.User;
import ru.chermashentsev.cloudcomputing.mapper.ProductReviewMapper;
import ru.chermashentsev.cloudcomputing.service.ProductReviewService;
import ru.chermashentsev.cloudcomputing.service.ProductService;
import ru.chermashentsev.cloudcomputing.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewConsumer {

    private final ProductReviewService productReviewService;
    private final ProductService productService;
    private final ProductReviewMapper productReviewMapper;
    private final UserService userService;

    @KafkaListener(topics = "${app.kafka.review-topic}")
    public void listen(CreateProductReviewRequestDTO requestDTO) {
        ProductReview productReview = productReviewMapper.toModel(requestDTO);

        Product product = productService.findById(requestDTO.getProductId());
        productReview.setProduct(product);

        User user = userService.findUserById(requestDTO.getUserId());
        productReview.setUser(user);

        productReviewService.save(productReview);
        log.info("Review created: {}", productReview);
    }

}
