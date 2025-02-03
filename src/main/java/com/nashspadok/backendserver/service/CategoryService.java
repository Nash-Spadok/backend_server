package com.nashspadok.backendserver.service;

import com.nashspadok.backendserver.dto.category.CategoryRequestDto;
import com.nashspadok.backendserver.dto.category.CategoryResponseDto;
import com.nashspadok.backendserver.dto.category.CategoryUpdateRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto updateCategory(CategoryUpdateRequestDto categoryRequestDto, Long id);

    void deleteCategory(Long id);

    CategoryResponseDto getCategory(Long id);

    List<CategoryResponseDto> getAllCategories(Pageable pageable);
}
