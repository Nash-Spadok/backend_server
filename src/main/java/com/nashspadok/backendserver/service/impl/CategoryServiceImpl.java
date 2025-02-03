package com.nashspadok.backendserver.service.impl;

import com.nashspadok.backendserver.dto.category.CategoryRequestDto;
import com.nashspadok.backendserver.dto.category.CategoryResponseDto;
import com.nashspadok.backendserver.dto.category.CategoryUpdateRequestDto;
import com.nashspadok.backendserver.exception.EntityNotFoundException;
import com.nashspadok.backendserver.mapper.CategoryMapper;
import com.nashspadok.backendserver.model.category.Category;
import com.nashspadok.backendserver.model.file.CategoryFile;
import com.nashspadok.backendserver.repository.CategoryRepository;
import com.nashspadok.backendserver.service.CategoryFileService;
import com.nashspadok.backendserver.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
        CategoryFile categoryFile = getCategoryFile(categoryRequestDto.getImage(), category);
        category.setCategoryFile(categoryFile);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponseDto updateCategory(CategoryUpdateRequestDto categoryRequestDto,
                                              Long id) {
        Category category = findCategoryById(id);
        categoryMapper.updateCategoryFromDto(categoryRequestDto, category);

        if (categoryRequestDto.getImage() != null) {
            CategoryFile categoryFile = getCategoryFile(categoryRequestDto.getImage(), category);
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
                () -> new EntityNotFoundException(String
                        .format("Category with id %d not exist", id))
        );
    }

    private void deleteImageFromS3(String imageUrl) {
        categoryFileService.delete(imageUrl);
    }

    private CategoryFile getCategoryFile(MultipartFile image, Category category) {
        return categoryFileService.create(image, category);
    }
}
