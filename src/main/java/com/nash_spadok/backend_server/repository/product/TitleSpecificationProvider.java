package com.nash_spadok.backend_server.repository.product;

import com.nash_spadok.backend_server.model.Product;
import com.nash_spadok.backend_server.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Product> {
    private static final String TITLE_PARAMETER = "title";

    @Override
    public String getKey() {
        return TITLE_PARAMETER;
    }

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(TITLE_PARAMETER)
                .in(Arrays.stream(params).toArray());
    }
}
