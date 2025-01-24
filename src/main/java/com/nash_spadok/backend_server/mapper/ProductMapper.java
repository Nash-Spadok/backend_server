package com.nash_spadok.backend_server.mapper;

import com.nash_spadok.backend_server.config.MapperConfig;
import com.nash_spadok.backend_server.dto.product.BookProductRequestDto;
import com.nash_spadok.backend_server.dto.product.BookProductRespondDto;
import com.nash_spadok.backend_server.dto.product.VyshyvankaProductRespondDto;
import com.nash_spadok.backend_server.model.product.Product;
import org.mapstruct.*;

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

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateProductFromDto(BookProductRequestDto productRequestDto, @MappingTarget Product product);

//    @AfterMapping
//    default void setProductSubCategoryIds(Product product, @MappingTarget BookProductRespondDto productRespondDto) {
//        if (product != null && product.getSubCategory() != null) {
//            productRespondDto.setSubCategoryId(product.getSubCategory().getId());
//        }
//    }
}
