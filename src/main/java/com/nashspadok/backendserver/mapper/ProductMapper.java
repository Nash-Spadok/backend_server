package com.nashspadok.backendserver.mapper;

import com.nashspadok.backendserver.config.MapperConfig;
import com.nashspadok.backendserver.dto.product.BookProductRequestDto;
import com.nashspadok.backendserver.dto.product.BookProductRespondDto;
import com.nashspadok.backendserver.dto.product.VyshyvankaProductRespondDto;
import com.nashspadok.backendserver.model.product.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/*
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    - this annotation is used to ignore null values in the mapping process.
 */
@Mapper(config = MapperConfig.class)
public interface ProductMapper {
    @Mapping(target = "images", ignore = true)
    Product toEntity(BookProductRequestDto productRequestDto);

    @Mapping(target = "images", ignore = true)
    BookProductRespondDto toBookDto(Product product);

    @Mapping(target = "images", ignore = true)
    VyshyvankaProductRespondDto toVyshyvankaDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(
            BookProductRequestDto productRequestDto,
            @MappingTarget Product product
    );
}
