package com.nashspadok.backendserver.service.impl;

import com.nashspadok.backendserver.dto.product.BookProductRequestDto;
import com.nashspadok.backendserver.exception.EntityNotFoundException;
import com.nashspadok.backendserver.mapper.ProductMapper;
import com.nashspadok.backendserver.model.category.SubCategory;
import com.nashspadok.backendserver.model.file.ProductFile;
import com.nashspadok.backendserver.model.product.BookProductDetails;
import com.nashspadok.backendserver.model.product.Product;
import com.nashspadok.backendserver.repository.SubCategoryRepository;
import com.nashspadok.backendserver.repository.product.ProductRepository;
import com.nashspadok.backendserver.service.ProductFileService;
import com.nashspadok.backendserver.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductFileService productFileService;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    @Transactional
    public ResponseEntity<?> createProduct(BookProductRequestDto productRequestDto) {
        Product product = productMapper.toEntity(productRequestDto);

        product.setSubCategory(findSubCategoryById(productRequestDto.getSubCategoryId()));
        List<ProductFile> productFiles = getProductFile(productRequestDto.getImages(), product);
        product.setImages(productFiles);
        if (product instanceof BookProductDetails) {
            return ResponseEntity.ok(productMapper.toBookDto(productRepository.save(product)));
        }
        return ResponseEntity.ok(productMapper.toVyshyvankaDto(productRepository.save(product)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateProduct(BookProductRequestDto productRequestDto, Long id) {
        Product product = findProductById(id);
        productMapper.updateProductFromDto(productRequestDto, product);
        if (productRequestDto.getImages() != null) {
            List<ProductFile> productFile = getProductFile(productRequestDto.getImages(), product);
            product.setImages(productFile);
        }
        if (product instanceof BookProductDetails) {
            return ResponseEntity.ok(productMapper.toBookDto(productRepository.save(product)));
        }
        return ResponseEntity.ok(productMapper.toVyshyvankaDto(productRepository.save(product)));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        deleteImageFromS3(product.getImages());
        productRepository.delete(product);
    }

    @Override
    public ResponseEntity<?> getProduct(Long id) {
        Product product = findProductById(id);
        if (product instanceof BookProductDetails) {
            return ResponseEntity.ok(productMapper.toBookDto(product));
        }
        return ResponseEntity.ok(productMapper.toVyshyvankaDto(product));
    }

    @Override
    public List<?> getProductsBySubCategoryId(Long id) {
        List<Product> list = productRepository.findBySubCategoryIdOrderByTitleAsc(id)
                .stream()
                .toList();
        if (list.getFirst() instanceof BookProductDetails) {
            return list.stream()
                    .map(productMapper::toBookDto)
                    .toList();
        }
        return list.stream()
                .map(productMapper::toVyshyvankaDto)
                .toList();
    }

    @Override
    public List<?> getProductsByCategoryId(Long id) {
        List<Product> list = productRepository.findAllByCategoryId(id)
                .stream()
                .toList();
        if (list.getFirst() instanceof BookProductDetails) {
            return list.stream()
                    .map(productMapper::toBookDto)
                    .toList();
        }
        return list.stream()
                .map(productMapper::toVyshyvankaDto)
                .toList();
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String
                        .format("Product with id %d not exist", id))
        );
    }

    private void deleteImageFromS3(List<ProductFile> imageUrl) {
        productFileService
                .delete(imageUrl
                        .stream()
                        .map(ProductFile::getUrl)
                        .toList());
    }

    private List<ProductFile> getProductFile(List<MultipartFile> images, Product product) {
        return images.stream()
                .map(image -> productFileService.create(image, product))
                .toList();
    }

    private SubCategory findSubCategoryById(Long id) {
        return subCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String
                        .format("SubCategory with id %d not exist", id))
        );
    }
}
