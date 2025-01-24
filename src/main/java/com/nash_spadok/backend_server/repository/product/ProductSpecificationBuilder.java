//package com.nash_spadok.backend_server.repository.product;
//
//import com.nash_spadok.backend_server.dto.product.ProductSearchDto;
//import com.nash_spadok.backend_server.model.product.Product;
//import com.nash_spadok.backend_server.repository.SpecificationBuilder;
//import com.nash_spadok.backend_server.repository.SpecificationProviderManager;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Component;
//
//@RequiredArgsConstructor
//@Component
//public class ProductSpecificationBuilder implements SpecificationBuilder<Product> {
//    private static final String PRICE_KEYWORD = "price";
//    private static final String TITLE_KEYWORD = "titles";
//    private final SpecificationProviderManager<Product> specificationProviderManager;
//
//    @Override
//    public Specification<Product> build(ProductSearchDto searchParameters) {
//        Specification<Product> spec = Specification.where(null);
//        if (searchParameters.prices() != null && searchParameters.prices().length > 0) {
//            spec = spec.and(specificationProviderManager
//                    .getSpecificationProvider(PRICE_KEYWORD)
//                    .getSpecification(searchParameters.prices()));
//        }
//        if (searchParameters.titles() != null && searchParameters.titles().length > 0) {
//            spec = spec.and(specificationProviderManager
//                    .getSpecificationProvider(TITLE_KEYWORD)
//                    .getSpecification(searchParameters.titles()));
//        }
//        return spec;
//    }
//}
