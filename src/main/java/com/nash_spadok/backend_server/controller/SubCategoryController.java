package com.nash_spadok.backend_server.controller;

import com.nash_spadok.backend_server.dto.SubCategoryRequestDto;
import com.nash_spadok.backend_server.dto.SubCategoryResponseDto;
import com.nash_spadok.backend_server.service.SubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subcategories")
@RequiredArgsConstructor
@Validated
@Tag(name = "SubCategory", description = "SubCategory API")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @PostMapping
    @Operation(summary = "Create subcategory")
    public SubCategoryResponseDto createSubCategory(@Valid @RequestBody SubCategoryRequestDto subCategoryRequestDto) {
        return subCategoryService.createSubCategory(subCategoryRequestDto);
    }

//    @GetMapping("/category/{categoryId}")
//    @Operation(summary = "Get subcategory by category id")
//    public List<SubCategoryResponseDto> getSubCategoryByCategoryId(@PathVariable Long categoryId) {
//        return subCategoryService.getSubCategoryByCategoryId(categoryId);
//    }

    @GetMapping
    @Operation(summary = "Get all subcategories")
    public List<SubCategoryResponseDto> getAllSubCategories(
            @ParameterObject @PageableDefault Pageable pageable) {
        return subCategoryService.getAllSubCategories(pageable);
    }
}
