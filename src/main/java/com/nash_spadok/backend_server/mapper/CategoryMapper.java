package com.nash_spadok.backend_server.mapper;

import com.nash_spadok.backend_server.config.MapperConfig;
import com.nash_spadok.backend_server.dto.category.CategoryRequestDto;
import com.nash_spadok.backend_server.dto.category.CategoryResponseDto;
import com.nash_spadok.backend_server.dto.category.CategoryUpdateRequestDto;
import com.nash_spadok.backend_server.model.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    Category toCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto toDto(Category category);

    void updateCategoryFromDto(CategoryUpdateRequestDto categoryRequestDto, @MappingTarget Category category);
}
