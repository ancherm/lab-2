package ru.chermashentsev.cloudcomputing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.chermashentsev.cloudcomputing.dto.request.product.CreateProductRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.request.product.UpdateProductRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.response.product.ProductResponseDTO;
import ru.chermashentsev.cloudcomputing.entity.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    Product toModel(CreateProductRequestDTO productDTO);

    ProductResponseDTO toDto(Product product);

    void updateProductFromDto(UpdateProductRequestDTO productDTO, @MappingTarget Product product);

}
