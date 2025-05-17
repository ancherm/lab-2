package ru.chermashentsev.cloudcomputing.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.chermashentsev.cloudcomputing.dto.request.product.review.CreateProductReviewRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.response.product.review.ProductReviewResponseDTO;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final KafkaTemplate<String, CreateProductReviewRequestDTO> kafkaTemplate;

    @Value("${app.url.review}")
    private String dataServiceUrl;

    @Value("${app.kafka.review-topic}")
    private String reviewTopic;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody CreateProductReviewRequestDTO requestDTO) {
        kafkaTemplate.send(reviewTopic, requestDTO);
        return ResponseEntity.ok("Successfully added review");
    }

    @GetMapping
    public ResponseEntity<List<ProductReviewResponseDTO>> getProducts() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ProductReviewResponseDTO>> productResponseDTOS = restTemplate.exchange(
                dataServiceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return ResponseEntity.ok(productResponseDTOS.getBody());
    }

}
