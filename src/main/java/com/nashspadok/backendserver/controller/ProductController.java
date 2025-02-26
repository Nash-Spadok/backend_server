package com.nashspadok.backendserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashspadok.backendserver.dto.product.BookProductRequestDto;
import com.nashspadok.backendserver.service.ProductService;
import com.nashspadok.backendserver.validation.ValidImageFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("api/v1/products")
@Validated
@RequiredArgsConstructor
@Tag(name = "product", description = "the Product API")
public class ProductController {
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create a product", description = "Create a product")
    public ResponseEntity<?> createProduct(
            @RequestPart("data") String data,
            @RequestPart("files") List<@ValidImageFile MultipartFile> files
    ) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data string cannot be null or empty");
        }
        BookProductRequestDto requestDto;
        try {
            requestDto = objectMapper.readValue(data, BookProductRequestDto.class);
            requestDto.setImages(files);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid data format: " + e.getMessage());
        }
        return ResponseEntity.ok(productService.createProduct(requestDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @Operation(summary = "Update a product", description = "Update a product")
    public ResponseEntity<?> updateProduct(
            @RequestPart("data") String data,
            @RequestPart("files") List<@ValidImageFile MultipartFile> files,
            @PathVariable Long id
    ) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data string cannot be null or empty");
        }
        BookProductRequestDto requestDto;
        try {
            requestDto = objectMapper.readValue(data, BookProductRequestDto.class);
            requestDto.setImages(files);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid data format: " + e.getMessage());
        }
        return ResponseEntity.ok(productService.updateProduct(requestDto, id));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product", description = "Get a product")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/subcategory/{id}")
    @Operation(summary = "Get products by subcategory", description = "Get products by subcategory")
    public ResponseEntity<List<?>> getProductsBySubCategory(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductsBySubCategoryId(id));
    }

    @GetMapping("/category/book/{id}")
    @Operation(summary = "Get products by category", description = "Get products by category")
    public ResponseEntity<List<?>> getProductsByCategoryBook(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductsByCategoryId(id));
    }
}
