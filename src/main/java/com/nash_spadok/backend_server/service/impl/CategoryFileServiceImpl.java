package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.model.category.Category;
import com.nash_spadok.backend_server.model.file.CategoryFile;
import com.nash_spadok.backend_server.repository.CategoryFileRepository;
import com.nash_spadok.backend_server.service.CategoryFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryFileServiceImpl implements CategoryFileService {

    @Override
    public CategoryFile create(String imageUrl, Category category) {
        CategoryFile categoryFile = new CategoryFile();
        categoryFile.setCategory(category);
        categoryFile.setUrl(imageUrl);
        return categoryFile;
    }
}
