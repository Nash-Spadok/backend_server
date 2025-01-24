package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.dto.product.BookProductRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<?> createProduct(BookProductRequestDto productRequestDto);
//
//    BookProductRespondDto updateProduct(BookProductRequestDto productRequestDto, Long id);
//
//    void deleteProduct(Long id);

    ResponseEntity<?> getProduct(Long id);

//    List<BookProductRespondDto> getAllBookProducts(Pageable pageable);
//
//    List<VyshyvankaProductRespondDto> getAllVyshyvankaProducts(Pageable pageable);

    List<?> getProductsBySubCategoryId(Long id);

    List<?> getProductsByCategoryId(Long id);

//    List<BookProductRespondDto> searchProducts(ProductSearchDto search, Pageable pageable, String sortOrder);
}
