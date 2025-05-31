package ru.chermashentsev.cloudcomputing.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.chermashentsev.cloudcomputing.dto.request.product.CreateProductRequestDTO;
import ru.chermashentsev.cloudcomputing.entity.Product;
import ru.chermashentsev.cloudcomputing.mapper.ProductMapper;
import ru.chermashentsev.cloudcomputing.service.ProductService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductConsumer {

    private final ProductMapper productMapper;
    private final ProductService productService;


    @KafkaListener(topics = "${app.kafka.product-topic}")
    public void listen(ConsumerRecord<String, CreateProductRequestDTO> record) {
        CreateProductRequestDTO requestDTO = record.value();
        Product product = productMapper.toModel(requestDTO);

        productService.save(product);
        log.info("Product created: {}", product);
    }

}
