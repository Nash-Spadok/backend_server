package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.dto.ProductRequestDto;
import com.nash_spadok.backend_server.dto.ProductRespondDto;

public interface ProductService {
    ProductRespondDto createProduct(ProductRequestDto productRequestDto);

    ProductRespondDto updateProduct(ProductRequestDto productRequestDto, Long id);

    void deleteProduct(Long id);

    ProductRespondDto getProduct(Long id);
}
