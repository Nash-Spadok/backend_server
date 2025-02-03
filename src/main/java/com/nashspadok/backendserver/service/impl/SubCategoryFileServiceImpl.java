package com.nashspadok.backendserver.service.impl;

import com.nashspadok.backendserver.exception.FileUploadException;
import com.nashspadok.backendserver.model.category.SubCategory;
import com.nashspadok.backendserver.model.file.SubCategoryFile;
import com.nashspadok.backendserver.service.SubCategoryFileService;
import com.nashspadok.backendserver.service.files.FileStorageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
