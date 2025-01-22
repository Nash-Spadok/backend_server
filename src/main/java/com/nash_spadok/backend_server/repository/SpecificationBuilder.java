package com.nash_spadok.backend_server.repository;

import com.nash_spadok.backend_server.dto.product.ProductSearchDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(ProductSearchDto productSearchDto);
}
