package com.nash_spadok.backend_server.controller;

import com.nash_spadok.backend_server.dto.product.ProductRequestDto;
import com.nash_spadok.backend_server.dto.product.ProductRespondDto;
import com.nash_spadok.backend_server.dto.product.ProductSearchDto;
import com.nash_spadok.backend_server.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@Validated
@RequiredArgsConstructor
@Tag(name = "product", description = "the Product API")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a product", description = "Create a product")
    public ResponseEntity<ProductRespondDto> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.createProduct(productRequestDto));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a product", description = "Update a product")
    public ResponseEntity<ProductRespondDto> updateProduct(@RequestBody @Valid ProductRequestDto productRequestDto,
                                                           @PathVariable Long id) {
        return ResponseEntity.ok(productService.updateProduct(productRequestDto, id));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product", description = "Get a product")
    public ResponseEntity<ProductRespondDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Get all products")
    public ResponseEntity<List<ProductRespondDto>> getAllProducts(
            @ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("/category/{id}")
    @Operation(summary = "Get products by category", description = "Get products by category")
    public ResponseEntity<List<ProductRespondDto>> getProductsByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductsByCategoryId(id));
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search products",
            description = "Search products by various criteria"
    )
    public ResponseEntity<List<ProductRespondDto>> searchProducts(
            @Valid @ParameterObject ProductSearchDto search,
            @ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(productService.searchProducts(search, pageable));
    }
}
