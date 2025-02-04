package com.nashspadok.backendserver.service;

import com.nashspadok.backendserver.model.category.Category;
import com.nashspadok.backendserver.model.file.CategoryFile;
import org.springframework.web.multipart.MultipartFile;

public interface CategoryFileService {
    CategoryFile create(MultipartFile image, Category category);

    void delete(String imageUrl);
}
