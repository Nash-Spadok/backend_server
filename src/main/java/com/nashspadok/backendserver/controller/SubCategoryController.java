package com.nashspadok.backendserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashspadok.backendserver.dto.SubCategoryRequestDto;
import com.nashspadok.backendserver.dto.SubCategoryResponseDto;
import com.nashspadok.backendserver.service.SubCategoryService;
import com.nashspadok.backendserver.validation.ValidImageFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
