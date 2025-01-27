package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.dto.category.CategoryRequestDto;
import com.nash_spadok.backend_server.dto.category.CategoryResponseDto;
import com.nash_spadok.backend_server.dto.category.CategoryUpdateRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto updateCategory(CategoryUpdateRequestDto categoryRequestDto, Long id);

    void deleteCategory(Long id);

    CategoryResponseDto getCategory(Long id);

    List<CategoryResponseDto> getAllCategories(Pageable pageable);
}
