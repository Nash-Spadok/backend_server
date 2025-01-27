package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.dto.category.CategoryRequestDto;
import com.nash_spadok.backend_server.dto.category.CategoryResponseDto;
import com.nash_spadok.backend_server.dto.category.CategoryUpdateRequestDto;
import com.nash_spadok.backend_server.exception.EntityNotFoundException;
import com.nash_spadok.backend_server.mapper.CategoryMapper;
import com.nash_spadok.backend_server.model.category.Category;
import com.nash_spadok.backend_server.model.file.CategoryFile;
import com.nash_spadok.backend_server.repository.CategoryRepository;
import com.nash_spadok.backend_server.service.CategoryFileService;
import com.nash_spadok.backend_server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryFileService categoryFileService;

    @Override
    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toCategory(categoryRequestDto);
        CategoryFile categoryFile = setCategoryFile(categoryRequestDto.getImage(), category);
        category.setCategoryFile(categoryFile);
        return categoryMapper.toDto(categoryRepository.save(category));
    }


    @Override
    @Transactional
    public CategoryResponseDto updateCategory(CategoryUpdateRequestDto categoryRequestDto, Long id) {
        Category category = findCategoryById(id);
        categoryMapper.updateCategoryFromDto(categoryRequestDto, category);

        if (categoryRequestDto.getImage() != null) {
            CategoryFile categoryFile = setCategoryFile(categoryRequestDto.getImage(), category);
            category.setCategoryFile(categoryFile);
        }
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = findCategoryById(id);
        deleteImageFromS3(category.getCategoryFile().getUrl());
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

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Category with id %d not exist", id))
        );
    }

    private void deleteImageFromS3(String imageUrl) {
        categoryFileService.delete(imageUrl);
    }

    private CategoryFile setCategoryFile(MultipartFile image, Category category) {
        return categoryFileService.create(image, category);
    }
}
