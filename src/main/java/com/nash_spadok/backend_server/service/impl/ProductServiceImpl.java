package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.dto.product.ProductRequestDto;
import com.nash_spadok.backend_server.dto.product.ProductRespondDto;
import com.nash_spadok.backend_server.dto.product.ProductSearchDto;
import com.nash_spadok.backend_server.exception.EntityNotFoundException;
import com.nash_spadok.backend_server.mapper.ProductMapper;
import com.nash_spadok.backend_server.model.Product;
import com.nash_spadok.backend_server.model.SubCategory;
import com.nash_spadok.backend_server.repository.SubCategoryRepository;
import com.nash_spadok.backend_server.repository.product.ProductRepository;
import com.nash_spadok.backend_server.repository.product.ProductSpecificationBuilder;
import com.nash_spadok.backend_server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductSpecificationBuilder specificationBuilder;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    @Transactional
    public ProductRespondDto createProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.toProduct(productRequestDto);
        product.setSubCategory(findSubCategoryById(productRequestDto.getSubCategoryId()));
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

    @Override
    public List<ProductRespondDto> getAllProducts(Pageable pageable) {
        return productRepository
                .findAll(pageable)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductRespondDto> getProductsBySubCategoryId(Long id) {
        return productRepository.findBySubCategoryIdOrderByTitleAsc(id)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductRespondDto> searchProducts(ProductSearchDto search, Pageable pageable, String sortOrder) {
        Specification<Product> specification = specificationBuilder.build(search);
        if (sortOrder.equals("desc")) {
            return productRepository.findAll(specification, pageable)
                    .stream()
                    .sorted(Comparator.comparing(Product::getTitle).reversed())
                    .map(productMapper::toDto)
                    .toList();
        }
        return productRepository.findAll(specification, pageable)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Product with id %d not exist", id))
        );
    }

    private SubCategory findSubCategoryById(Long id) {
        return subCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("SubCategory with id %d not exist", id))
        );
    }
}
