package com.nashspadok.backendserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashspadok.backendserver.dto.category.CategoryRequestDto;
import com.nashspadok.backendserver.dto.category.CategoryResponseDto;
import com.nashspadok.backendserver.dto.category.CategoryUpdateRequestDto;
import com.nashspadok.backendserver.dto.category.CategoryWithoutSubcategoriesResponseDto;
import com.nashspadok.backendserver.service.CategoryService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/categories")
@Validated
@RequiredArgsConstructor
@Tag(name = "Category", description = "Category API")
public class CategoryController {
    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "", description = "")
    public ResponseEntity<CategoryWithoutSubcategoriesResponseDto> createCategory(
            @RequestPart("data") String data,
            @RequestPart("file") @ValidImageFile MultipartFile file
    ) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data string cannot be null or empty");
        }
        CategoryRequestDto requestDto;
        try {
            requestDto = objectMapper.readValue(data, CategoryRequestDto.class);
            requestDto.setImage(file);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid data format: " + e.getMessage());
        }
        return ResponseEntity.ok(categoryService.createCategory(requestDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/{id}")
    @Operation(summary = "Update category")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @RequestPart("data") String data,
            @RequestPart("file") @ValidImageFile MultipartFile file,
            @PathVariable Long id
    ) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data string cannot be null or empty");
        }
        CategoryUpdateRequestDto requestDto;
        try {
            requestDto = objectMapper.readValue(data, CategoryUpdateRequestDto.class);
            requestDto.setImage(file);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid data format: " + e.getMessage());
        }
        return ResponseEntity.ok(categoryService.updateCategory(requestDto, id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @GetMapping
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(
            @ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(categoryService.getAllCategories(pageable));
    }

    @GetMapping("/without_subcategories")
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<CategoryWithoutSubcategoriesResponseDto>>
                    getAllCategoriesWithoutSubcategories(
            @ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(categoryService.getAllCategoriesWithoutSubcategories(pageable));
    }
}
