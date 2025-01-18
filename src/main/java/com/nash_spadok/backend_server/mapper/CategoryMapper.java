package com.nash_spadok.backend_server.mapper;

import com.nash_spadok.backend_server.config.MapperConfig;
import com.nash_spadok.backend_server.dto.category.CategoryRequestDto;
import com.nash_spadok.backend_server.dto.category.CategoryResponseDto;
import com.nash_spadok.backend_server.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    Category toCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto toDto(Category category);

    void updateCategoryFromDto(CategoryRequestDto categoryRequestDto, @MappingTarget Category category);
}
