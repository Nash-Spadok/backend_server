package com.nashspadok.backendserver.service.impl;

import com.nashspadok.backendserver.exception.FileUploadException;
import com.nashspadok.backendserver.model.file.ProductFile;
import com.nashspadok.backendserver.model.product.Product;
import com.nashspadok.backendserver.service.ProductFileService;
import com.nashspadok.backendserver.service.files.FileStorageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
                .orElseThrow(() ->
                        new FileUploadException("File uploaded " + image + " incorrectly"));
    }
}
