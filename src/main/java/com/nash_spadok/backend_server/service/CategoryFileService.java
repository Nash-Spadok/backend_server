package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.model.category.Category;
import com.nash_spadok.backend_server.model.file.CategoryFile;
import org.springframework.web.multipart.MultipartFile;

public interface CategoryFileService {
    CategoryFile create(MultipartFile image, Category category);
}
