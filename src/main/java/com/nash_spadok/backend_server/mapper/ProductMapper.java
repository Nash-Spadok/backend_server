package com.nash_spadok.backend_server.mapper;

import com.nash_spadok.backend_server.config.MapperConfig;
import com.nash_spadok.backend_server.dto.product.ProductRequestDto;
import com.nash_spadok.backend_server.dto.product.ProductRespondDto;
import com.nash_spadok.backend_server.model.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/*
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    - this annotation is used to ignore null values in the mapping process.
 */
@Mapper(config = MapperConfig.class)
public interface ProductMapper {
    Product toProduct(ProductRequestDto productRequestDto);

    ProductRespondDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductRequestDto productRequestDto, @MappingTarget Product product);

    @AfterMapping
    default void setProductCategory(Product product, @MappingTarget ProductRespondDto productRespondDto) {
        if (product != null && product.getCategory() != null) {
            productRespondDto.setCategoryId(product.getCategory().getId());
        }
    }
}
