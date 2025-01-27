package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.exception.FileUploadException;
import com.nash_spadok.backend_server.model.category.SubCategory;
import com.nash_spadok.backend_server.model.file.SubCategoryFile;
import com.nash_spadok.backend_server.service.SubCategoryFileService;
import com.nash_spadok.backend_server.service.files.FileStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SubCategoryFileServiceImpl implements SubCategoryFileService {
    private static final String FOLDER_NAME = "SubCategory";
    @Qualifier(value = "s3Service")
    private final FileStorageService fileStorageService;

    public SubCategoryFileServiceImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    public SubCategoryFile create(MultipartFile image, SubCategory subCategory) {
        SubCategoryFile subCategoryFile = new SubCategoryFile();
        subCategoryFile.setSubCategory(subCategory);
        subCategoryFile.setUrl(getUrl(image));
        return subCategoryFile;
    }

    private String getUrl(MultipartFile image) {
        return fileStorageService
                .uploadFileToS3(FOLDER_NAME, List.of(image))
                .stream()
                .findFirst()
                .orElseThrow(() -> new FileUploadException("Can`t upload file " + image));
    }
}
