package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.dto.SubCategoryRequestDto;
import com.nash_spadok.backend_server.dto.SubCategoryResponseDto;
import com.nash_spadok.backend_server.exception.EntityNotFoundException;
import com.nash_spadok.backend_server.mapper.SubCategoryMapper;
import com.nash_spadok.backend_server.model.Category;
import com.nash_spadok.backend_server.model.SubCategory;
import com.nash_spadok.backend_server.repository.SubCategoryRepository;
import com.nash_spadok.backend_server.repository.CategoryRepository;
import com.nash_spadok.backend_server.service.SubCategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryMapper subCategoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public SubCategoryResponseDto createSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        SubCategory subCategory = subCategoryMapper.toEntity(subCategoryRequestDto);
        Category category = findCategoryById(subCategoryRequestDto.getCategoryId());
        subCategory.setCategory(category);
        return subCategoryMapper.toDto(subCategoryRepository.save(subCategory));
    }

    @Override
    public List<SubCategoryResponseDto> getSubCategoryByCategoryId(Long id) {
        return subCategoryRepository
                .findAllByCategoryId(id)
                .stream()
                .map(subCategoryMapper::toDto)
                .toList();
    }

    @Override
    public List<SubCategoryResponseDto> getAllSubCategories(Pageable pageable) {
        return subCategoryRepository
                .findAll(pageable)
                .stream()
                .map(subCategoryMapper::toDto)
                .toList();
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Category with id %d not exist", id))
        );
    }
}
