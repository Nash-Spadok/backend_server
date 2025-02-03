package com.nashspadok.backendserver.service.impl;

import com.nashspadok.backendserver.dto.SubCategoryRequestDto;
import com.nashspadok.backendserver.dto.SubCategoryResponseDto;
import com.nashspadok.backendserver.exception.EntityNotFoundException;
import com.nashspadok.backendserver.mapper.SubCategoryMapper;
import com.nashspadok.backendserver.model.category.Category;
import com.nashspadok.backendserver.model.category.SubCategory;
import com.nashspadok.backendserver.model.file.SubCategoryFile;
import com.nashspadok.backendserver.repository.CategoryRepository;
import com.nashspadok.backendserver.repository.SubCategoryRepository;
import com.nashspadok.backendserver.service.SubCategoryFileService;
import com.nashspadok.backendserver.service.SubCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryMapper subCategoryMapper;
    private final CategoryRepository categoryRepository;
    private final SubCategoryFileService subCategoryFileService;

    @Override
    public SubCategoryResponseDto createSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        SubCategory subCategory = subCategoryMapper.toEntity(subCategoryRequestDto);
        Category category = findCategoryById(subCategoryRequestDto.getCategoryId());
        subCategory.setCategory(category);
        SubCategoryFile subCategoryFile = getSubCategoryFile(
                subCategoryRequestDto.getImage(), subCategory
        );
        subCategory.setSubCategoryFile(subCategoryFile);
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
                () -> new EntityNotFoundException(String
                        .format("Category with id %d not exist", id))
        );
    }

    private SubCategoryFile getSubCategoryFile(MultipartFile image, SubCategory subCategory) {
        return subCategoryFileService.create(image, subCategory);
    }
}
