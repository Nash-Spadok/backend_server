package com.nash_spadok.backend_server.controller;

import com.nash_spadok.backend_server.dto.product.BookProductRequestDto;
import com.nash_spadok.backend_server.dto.product.BookProductRespondDto;
import com.nash_spadok.backend_server.model.product.VyshyvankaProductDetails;
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
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createProduct(@RequestBody @Valid BookProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.createProduct(productRequestDto));
    }
//
//    @PatchMapping("/{id}")
//    @Operation(summary = "Update a product", description = "Update a product")
//    public ResponseEntity<BookProductRespondDto> updateProduct(@RequestBody @Valid BookProductRequestDto productRequestDto,
//                                                               @PathVariable Long id) {
//        return ResponseEntity.ok(productService.updateProduct(productRequestDto, id));
//
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Delete a product", description = "Delete a product")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.ok().build();
//    }

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









    // no need
//    @GetMapping
//    @Operation(summary = "Get all products", description = "Get all products")
//    public ResponseEntity<List<BookProductRespondDto>> getAllProducts(
//            @ParameterObject @PageableDefault Pageable pageable) {
//        return ResponseEntity.ok(productService.getAllProducts(pageable));
//    }


//


//    @GetMapping("/search")
//    @Operation(
//            summary = "Search products",
//            description = "Search products by various criteria with sorting"
//    )
//    public ResponseEntity<List<BookProductRespondDto>> searchProducts(
//            @Valid @ParameterObject ProductSearchDto search,
//            @ParameterObject @PageableDefault Pageable pageable,
//            @RequestParam String sortOrder) {
//        return ResponseEntity.ok(productService.searchProducts(search, pageable, sortOrder));
//    }
}
