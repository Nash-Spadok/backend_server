package com.nashspadok.backendserver.service;

import com.nashspadok.backendserver.model.category.SubCategory;
import com.nashspadok.backendserver.model.file.SubCategoryFile;
import org.springframework.web.multipart.MultipartFile;

public interface SubCategoryFileService {
    SubCategoryFile create(MultipartFile image, SubCategory subCategory);
}
