package ru.chermashentsev.cloudcomputing.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chermashentsev.cloudcomputing.client.ApiClient;
import ru.chermashentsev.cloudcomputing.dto.request.product.CreateProductRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.response.product.ProductResponseDTO;
import ru.chermashentsev.cloudcomputing.kafka.KafkaProducer;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final KafkaProducer kafkaProducer;
    private final ApiClient apiClient;


    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody CreateProductRequestDTO requestDTO) {
        kafkaProducer.sendProduct(requestDTO);
        return ResponseEntity.ok("Successfully added product");
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        return ResponseEntity.ok(apiClient.getAllProducts().getBody());
    }


}
