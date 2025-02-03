package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.dto.product.BookProductRequestDto;
import com.nash_spadok.backend_server.exception.EntityNotFoundException;
import com.nash_spadok.backend_server.mapper.ProductMapper;
import com.nash_spadok.backend_server.model.category.SubCategory;
import com.nash_spadok.backend_server.model.file.ProductFile;
import com.nash_spadok.backend_server.model.product.BookProductDetails;
import com.nash_spadok.backend_server.model.product.Product;
import com.nash_spadok.backend_server.repository.SubCategoryRepository;
import com.nash_spadok.backend_server.repository.product.ProductRepository;
import com.nash_spadok.backend_server.service.ProductFileService;
import com.nash_spadok.backend_server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductFileService productFileService;
    //    private final ProductSpecificationBuilder specificationBuilder;
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

    //    @Override
//    public List<BookProductRespondDto> searchProducts(ProductSearchDto search, Pageable pageable, String sortOrder) {
//        Specification<Product> specification = specificationBuilder.build(search);
//        if (sortOrder.equals("desc")) {
//            return productRepository.findAll(specification, pageable)
//                    .stream()
//                    .sorted(Comparator.comparing(Product::getTitle).reversed())
//                    .map(productMapper::toDto)
//                    .toList();
//        }
//        return productRepository.findAll(specification, pageable)
//                .stream()
//                .map(productMapper::toDto)
//                .toList();
//    }
    private Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Product with id %d not exist", id))
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
                () -> new EntityNotFoundException(String.format("SubCategory with id %d not exist", id))
        );
    }
}
