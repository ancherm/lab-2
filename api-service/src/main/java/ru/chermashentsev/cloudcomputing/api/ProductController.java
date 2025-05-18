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

    @Value("${app.url.data-service}")
    private String dataServiceUrl;

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
                dataServiceUrl + "/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return ResponseEntity.ok(productResponseDTOS.getBody());
    }


}
