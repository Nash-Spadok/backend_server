package com.nashspadok.backendserver.mapper;

import com.nashspadok.backendserver.config.MapperConfig;
import com.nashspadok.backendserver.dto.SubCategoryRequestDto;
import com.nashspadok.backendserver.dto.SubCategoryResponseDto;
import com.nashspadok.backendserver.model.category.SubCategory;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface SubCategoryMapper {
    SubCategory toEntity(SubCategoryRequestDto subCategoryRequestDto);

    SubCategoryResponseDto toDto(SubCategory subCategory);

    @AfterMapping
    default void setSubCategoryIds(
            SubCategory subCategory,
            @MappingTarget SubCategoryResponseDto subCategoryResponseDto
    ) {
        if (subCategory != null && subCategory.getCategory() != null) {
            subCategoryResponseDto.setCategoryId(subCategory.getCategory().getId());
        }
    }
}
