package com.nashspadok.backendserver.service.impl;

import com.nashspadok.backendserver.dto.category.CategoryRequestDto;
import com.nashspadok.backendserver.dto.category.CategoryResponseDto;
import com.nashspadok.backendserver.dto.category.CategoryUpdateRequestDto;
import com.nashspadok.backendserver.dto.category.CategoryWithoutSubcategoriesResponseDto;
import com.nashspadok.backendserver.exception.EntityNotFoundException;
import com.nashspadok.backendserver.mapper.CategoryMapper;
import com.nashspadok.backendserver.model.category.Category;
import com.nashspadok.backendserver.repository.CategoryRepository;
import com.nashspadok.backendserver.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryWithoutSubcategoriesResponseDto createCategory(
            CategoryRequestDto categoryRequestDto
    ) {
        Category category = categoryMapper.toCategory(categoryRequestDto);
        return categoryMapper.toDtoWithoutSubcategories(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponseDto updateCategory(CategoryUpdateRequestDto categoryRequestDto,
                                              Long id) {
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

    @Override
    public List<CategoryResponseDto> getAllCategories(Pageable pageable) {
        return categoryRepository
                .findAll(pageable)
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public List<CategoryWithoutSubcategoriesResponseDto> getAllCategoriesWithoutSubcategories(
            Pageable pageable
    ) {
        return categoryRepository
                .findAll(pageable)
                .stream()
                .map(categoryMapper::toDtoWithoutSubcategories)
                .toList();
    }


    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String
                        .format("Category with id %d not exist", id))
        );
    }
}
