package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.exception.FileUploadException;
import com.nash_spadok.backend_server.model.file.ProductFile;
import com.nash_spadok.backend_server.model.product.Product;
import com.nash_spadok.backend_server.service.ProductFileService;
import com.nash_spadok.backend_server.service.files.FileStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductFileServiceImpl implements ProductFileService {
    private static final String FOLDER_NAME = "Product";
    @Qualifier(value = "s3Service")
    private final FileStorageService fileStorageService;

    public ProductFileServiceImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    public ProductFile create(MultipartFile image, Product product) {
        ProductFile productFile = new ProductFile();
        productFile.setProduct(product);
        productFile.setUrl(getImageUrl(image));
        return productFile;
    }

    @Override
    public void delete(List<String> imageUrl) {
        imageUrl.forEach(fileStorageService::deleteFileFromS3);
    }

    private String getImageUrl(MultipartFile image) {
        return fileStorageService
                .uploadFileToS3(FOLDER_NAME, List.of(image))
                .stream()
                .findFirst()
                .orElseThrow(() -> new FileUploadException("File uploaded " + image + " incorrectly"));
    }
}
