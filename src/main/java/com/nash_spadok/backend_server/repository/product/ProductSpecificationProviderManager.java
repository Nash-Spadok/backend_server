package com.nash_spadok.backend_server.repository.product;

import com.nash_spadok.backend_server.exception.SpecificationNotFoundException;
import com.nash_spadok.backend_server.model.Product;
import com.nash_spadok.backend_server.repository.SpecificationProvider;
import com.nash_spadok.backend_server.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductSpecificationProviderManager implements SpecificationProviderManager<Product> {
    private final List<SpecificationProvider<Product>> specificationProviders;

    @Override
    public SpecificationProvider<Product> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(b -> b.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new SpecificationNotFoundException
                        ("Can`t find correct specification provider for key: " + key));
    }
}
