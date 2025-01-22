package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.dto.SubCategoryRequestDto;
import com.nash_spadok.backend_server.dto.SubCategoryResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubCategoryService {
    SubCategoryResponseDto createSubCategory(SubCategoryRequestDto subCategoryRequestDto);

    List<SubCategoryResponseDto> getSubCategoryByCategoryId(Long id);

    List<SubCategoryResponseDto> getAllSubCategories(Pageable pageable);
}
