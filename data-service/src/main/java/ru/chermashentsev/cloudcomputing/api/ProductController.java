package ru.chermashentsev.cloudcomputing.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chermashentsev.cloudcomputing.dto.request.product.CreateProductRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.request.product.UpdateProductRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.response.product.ProductResponseDTO;
import ru.chermashentsev.cloudcomputing.entity.Product;
import ru.chermashentsev.cloudcomputing.mapper.ProductMapper;
import ru.chermashentsev.cloudcomputing.service.ProductService;

import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> productResponseDTOList = productService.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();

        return ResponseEntity.ok(productResponseDTOList);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody CreateProductRequestDTO productRequestDTO) {

        Product product = productMapper.toModel(productRequestDTO);

        Product savedProduct = productService.save(product);

        ProductResponseDTO productResponseDTO = productMapper.toDto(savedProduct);

        return ResponseEntity.ok(productResponseDTO);
    }

    @PutMapping(value = "{ID}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseDTO> updateProduct(@Valid @RequestBody UpdateProductRequestDTO productRequestDTO,
                                                            @PathVariable("ID") long id) {

        Product product = productService.findById(id);

        productMapper.updateProductFromDto(productRequestDTO, product);
        Product updatedProduct = productService.update(product);

        ProductResponseDTO productResponseDTO = productMapper.toDto(updatedProduct);

        return ResponseEntity.ok(productResponseDTO);
    }

    @DeleteMapping(value = "{ID}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable("ID") long id) {

        Product product = productService.delete(id);

        ProductResponseDTO productResponseDTO = productMapper.toDto(product);

        return ResponseEntity.ok(productResponseDTO);
    }

}
