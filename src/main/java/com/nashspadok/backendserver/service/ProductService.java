package com.nashspadok.backendserver.service;

import com.nashspadok.backendserver.dto.product.BookProductRequestDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> createProduct(BookProductRequestDto productRequestDto);

    ResponseEntity<?> updateProduct(BookProductRequestDto productRequestDto, Long id);

    void deleteProduct(Long id);

    ResponseEntity<?> getProduct(Long id);

    List<?> getProductsBySubCategoryId(Long id);

    // List<BookProductRespondDto> searchProducts(
    // ProductSearchDto search, Pageable pageable, String sortOrder);
    List<?> getProductsByCategoryId(Long id);

}
