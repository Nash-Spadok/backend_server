package com.nash_spadok.backend_server.controller;

import com.nash_spadok.backend_server.dto.ProductRequestDto;
import com.nash_spadok.backend_server.dto.ProductRespondDto;
import com.nash_spadok.backend_server.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/products")
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
}
