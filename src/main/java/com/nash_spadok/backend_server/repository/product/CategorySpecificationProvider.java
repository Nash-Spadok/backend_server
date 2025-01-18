package com.nash_spadok.backend_server.repository.product;

import com.nash_spadok.backend_server.model.Category;
import com.nash_spadok.backend_server.model.Product;
import com.nash_spadok.backend_server.repository.SpecificationProvider;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategorySpecificationProvider implements SpecificationProvider<Product> {
    private static final String CATEGORY_PARAMETER = "category";
    private static final String NAME_FIELD = "name";
    private static final String TITLE_FIELD = "title";

    @Override
    public String getKey() {
        return CATEGORY_PARAMETER;
    }

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Join<Product, Category> categoryJoin = root.join(CATEGORY_PARAMETER);
            query.orderBy(criteriaBuilder.asc(root.get(TITLE_FIELD)));
            return categoryJoin.get(NAME_FIELD).in(Arrays.stream(params).toArray());
        };
    }
}
