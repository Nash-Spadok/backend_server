package com.nashspadok.backendserver.controller;

import com.nashspadok.backendserver.dto.category.CategoryRequestDto;
import com.nashspadok.backendserver.dto.category.CategoryResponseDto;
import com.nashspadok.backendserver.dto.category.CategoryUpdateRequestDto;
import com.nashspadok.backendserver.dto.category.CategoryWithoutSubcategoriesResponseDto;
import com.nashspadok.backendserver.service.CategoryService;
import com.nashspadok.backendserver.service.SubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
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

@RestController
@RequestMapping("api/v1/categories")
@Validated
@RequiredArgsConstructor
@Tag(name = "Category", description = "Category API")
public class CategoryController {
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "", description = "")
    public ResponseEntity<CategoryWithoutSubcategoriesResponseDto> createCategory(
            CategoryRequestDto requestDto
    ) {
        return ResponseEntity.ok(categoryService.createCategory(requestDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @Operation(summary = "Update category")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @RequestBody @Valid CategoryUpdateRequestDto requestDto,
            @PathVariable Long id
    ) {
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

    @GetMapping("/subcategory/{id}")
    @Operation(summary = "Get category by subcategory")
    public ResponseEntity<CategoryWithoutSubcategoriesResponseDto> getCategoryBySubcategory(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(subCategoryService.getBySubcategory(id));
    }
}
