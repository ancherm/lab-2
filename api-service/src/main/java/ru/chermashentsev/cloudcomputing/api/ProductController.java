package ru.chermashentsev.cloudcomputing.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.chermashentsev.cloudcomputing.dto.request.product.CreateProductRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.response.product.ProductResponseDTO;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final KafkaTemplate<String, CreateProductRequestDTO> kafkaTemplate;
    private final static String DATA_SERVICE_URL = "http://localhost:8082/api/products";
//    private final RestTemplate restTemplate;

    @Value("${app.kafka.product-topic}")
    private String productTopic;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody CreateProductRequestDTO requestDTO) {
        kafkaTemplate.send(productTopic, requestDTO);
        return ResponseEntity.ok("Successfully added product");
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ProductResponseDTO>> productResponseDTOS = restTemplate.exchange(
                DATA_SERVICE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return ResponseEntity.ok(productResponseDTOS.getBody());
    }


}
