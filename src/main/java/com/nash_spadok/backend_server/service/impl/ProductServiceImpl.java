package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.dto.ProductRequestDto;
import com.nash_spadok.backend_server.dto.ProductRespondDto;
import com.nash_spadok.backend_server.exception.EntityNotFoundException;
import com.nash_spadok.backend_server.mapper.ProductMapper;
import com.nash_spadok.backend_server.model.Category;
import com.nash_spadok.backend_server.model.Product;
import com.nash_spadok.backend_server.repository.CategoryRepository;
import com.nash_spadok.backend_server.repository.ProductRepository;
import com.nash_spadok.backend_server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductRespondDto createProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.toProduct(productRequestDto);
        product.setCategory(findCategoryById(productRequestDto.getCategoryId()));
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductRespondDto updateProduct(ProductRequestDto productRequestDto, Long id) {
        Product product = findProductById(id);
        productMapper.updateProductFromDto(productRequestDto, product);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    @Override
    public ProductRespondDto getProduct(Long id) {
        Product product = findProductById(id);
        return productMapper.toDto(product);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Product with id %d not exist", id))
        );
    }

    private Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Category with id %d not exist", categoryId))
        );
    }
}
