//package com.nash_spadok.backend_server.repository.product;
//
//import com.nash_spadok.backend_server.model.product.Product;
//import com.nash_spadok.backend_server.repository.SpecificationProvider;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//public class PriceSpecificationProvider implements SpecificationProvider<Product> {
//    private static final String PRICE_PARAMETER = "price";
//
//    @Override
//    public String getKey() {
//        return PRICE_PARAMETER;
//    }
//
//    @Override
//    public Specification<Product> getSpecification(String[] params) {
//        return (root, query, criteriaBuilder) -> root.get(PRICE_PARAMETER)
//                .in(Arrays.stream(params).toArray());
//    }
//}
