package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.model.category.SubCategory;
import com.nash_spadok.backend_server.model.file.SubCategoryFile;
import org.springframework.web.multipart.MultipartFile;

public interface SubCategoryFileService {
    SubCategoryFile create(MultipartFile image, SubCategory subCategory);
}
