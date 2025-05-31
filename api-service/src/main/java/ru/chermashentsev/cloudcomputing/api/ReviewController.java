package ru.chermashentsev.cloudcomputing.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chermashentsev.cloudcomputing.client.ApiClient;
import ru.chermashentsev.cloudcomputing.dto.request.product.review.CreateProductReviewRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.response.product.review.ProductReviewResponseDTO;
import ru.chermashentsev.cloudcomputing.kafka.KafkaProducer;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final KafkaProducer kafkaProducer;
    private final ApiClient apiClient;


    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody CreateProductReviewRequestDTO requestDTO) {
        kafkaProducer.sendReview(requestDTO);
        return ResponseEntity.ok("Successfully added review");
    }

    @GetMapping
    public ResponseEntity<List<ProductReviewResponseDTO>> getProducts() {
        return ResponseEntity.ok(apiClient.getAllProductReviews().getBody());
    }

}
