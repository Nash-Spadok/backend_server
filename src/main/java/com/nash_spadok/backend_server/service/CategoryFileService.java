package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.model.category.Category;
import com.nash_spadok.backend_server.model.file.CategoryFile;

public interface CategoryFileService {
    CategoryFile create(String imageUrl, Category category);
}
