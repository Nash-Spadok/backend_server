package com.nash_spadok.backend_server.mapper;

import com.nash_spadok.backend_server.config.MapperConfig;
import com.nash_spadok.backend_server.dto.SubCategoryRequestDto;
import com.nash_spadok.backend_server.dto.SubCategoryResponseDto;
import com.nash_spadok.backend_server.model.SubCategory;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface SubCategoryMapper {
    SubCategory toEntity(SubCategoryRequestDto subCategoryRequestDto);

    SubCategoryResponseDto toDto(SubCategory subCategory);

    @AfterMapping
    default void setSubCategoryIds(SubCategory subCategory, @MappingTarget SubCategoryResponseDto subCategoryResponseDto) {
        if (subCategory != null && subCategory.getCategory() != null) {
            subCategoryResponseDto.setCategoryId(subCategory.getCategory().getId());
        }
    }
}
