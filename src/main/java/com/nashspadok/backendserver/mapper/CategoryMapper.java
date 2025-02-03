package com.nashspadok.backendserver.mapper;

import com.nashspadok.backendserver.config.MapperConfig;
import com.nashspadok.backendserver.dto.category.CategoryRequestDto;
import com.nashspadok.backendserver.dto.category.CategoryResponseDto;
import com.nashspadok.backendserver.dto.category.CategoryUpdateRequestDto;
import com.nashspadok.backendserver.model.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    Category toCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto toDto(Category category);

    void updateCategoryFromDto(
            CategoryUpdateRequestDto categoryRequestDto,
            @MappingTarget Category category
    );
}
