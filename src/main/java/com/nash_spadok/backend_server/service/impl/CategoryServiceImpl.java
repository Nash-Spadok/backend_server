package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.dto.category.CategoryRequestDto;
import com.nash_spadok.backend_server.dto.category.CategoryResponseDto;
import com.nash_spadok.backend_server.exception.EntityNotFoundException;
import com.nash_spadok.backend_server.mapper.CategoryMapper;
import com.nash_spadok.backend_server.model.Category;
import com.nash_spadok.backend_server.repository.CategoryRepository;
import com.nash_spadok.backend_server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toCategory(categoryRequestDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, Long id) {
        Category category = findCategoryById(id);
        categoryMapper.updateCategoryFromDto(categoryRequestDto, category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponseDto getCategory(Long id) {
        Category category = findCategoryById(id);
        return categoryMapper.toDto(category);
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Category with id %d not exist", id))
        );
    }
}
