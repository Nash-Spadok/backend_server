package com.nashspadok.backendserver.service.impl;

import com.nashspadok.backendserver.exception.FileUploadException;
import com.nashspadok.backendserver.model.category.Category;
import com.nashspadok.backendserver.model.file.CategoryFile;
import com.nashspadok.backendserver.service.CategoryFileService;
import com.nashspadok.backendserver.service.files.FileStorageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CategoryFileServiceImpl implements CategoryFileService {
    private static final String FOLDER_NAME = "Category";
    @Qualifier(value = "s3Service")
    private final FileStorageService fileStorageService;

    public CategoryFileServiceImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    @Transactional
    public CategoryFile create(MultipartFile image, Category category) {
        CategoryFile categoryFile = new CategoryFile();
        categoryFile.setCategory(category);
        categoryFile.setUrl(getImageUrl(image));
        return categoryFile;
    }

    @Override
    @Transactional
    public void delete(String imageUrl) {
        fileStorageService.deleteFileFromS3(imageUrl);
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
