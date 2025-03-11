package com.nashspadok.backendserver.service;

import com.nashspadok.backendserver.dto.SubCategoryRequestDto;
import com.nashspadok.backendserver.dto.SubCategoryResponseDto;
import java.util.List;
import com.nashspadok.backendserver.dto.category.CategoryWithoutSubcategoriesResponseDto;
import org.springframework.data.domain.Pageable;

public interface SubCategoryService {
    SubCategoryResponseDto createSubCategory(SubCategoryRequestDto subCategoryRequestDto);

    List<SubCategoryResponseDto> getSubCategoryByCategoryId(Long id);

    List<SubCategoryResponseDto> getAllSubCategories(Pageable pageable);

    CategoryWithoutSubcategoriesResponseDto getBySubcategory(Long id);
}
