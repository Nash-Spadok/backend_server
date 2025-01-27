package com.nash_spadok.backend_server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nash_spadok.backend_server.dto.SubCategoryRequestDto;
import com.nash_spadok.backend_server.dto.SubCategoryResponseDto;
import com.nash_spadok.backend_server.dto.category.CategoryRequestDto;
import com.nash_spadok.backend_server.dto.category.CategoryResponseDto;
import com.nash_spadok.backend_server.service.SubCategoryService;
import com.nash_spadok.backend_server.validation.ValidImageFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/subcategories")
@RequiredArgsConstructor
@Validated
@Tag(name = "SubCategory", description = "SubCategory API")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    private final ObjectMapper objectMapper;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create subcategory")
    public ResponseEntity<SubCategoryResponseDto> createSubCategory(
            @RequestPart("data") String data,
            @RequestPart("file") @ValidImageFile MultipartFile file
    ) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data string cannot be null or empty");
        }
        SubCategoryRequestDto requestDto;
        try {
            requestDto = objectMapper.readValue(data, SubCategoryRequestDto.class);
            requestDto.setImage(file);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid data format: " + e.getMessage());
        }
        return ResponseEntity.ok(subCategoryService.createSubCategory(requestDto));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get subcategory by category id")
    public List<SubCategoryResponseDto> getSubCategoryByCategoryId(@PathVariable Long categoryId) {
        return subCategoryService.getSubCategoryByCategoryId(categoryId);
    }

    @GetMapping
    @Operation(summary = "Get all subcategories")
    public List<SubCategoryResponseDto> getAllSubCategories(
            @ParameterObject @PageableDefault Pageable pageable) {
        return subCategoryService.getAllSubCategories(pageable);
    }
}
