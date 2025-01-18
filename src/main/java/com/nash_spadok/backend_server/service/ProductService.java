package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.dto.product.ProductRequestDto;
import com.nash_spadok.backend_server.dto.product.ProductRespondDto;
import com.nash_spadok.backend_server.dto.product.ProductSearchDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductRespondDto createProduct(ProductRequestDto productRequestDto);

    ProductRespondDto updateProduct(ProductRequestDto productRequestDto, Long id);

    void deleteProduct(Long id);

    ProductRespondDto getProduct(Long id);

    List<ProductRespondDto> getAllProducts(Pageable pageable);

    List<ProductRespondDto> getProductsByCategoryId(Long id);

    List<ProductRespondDto> searchProducts(ProductSearchDto search, Pageable pageable);
}
