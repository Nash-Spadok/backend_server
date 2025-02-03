package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.model.file.ProductFile;
import com.nash_spadok.backend_server.model.product.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductFileService {
    ProductFile create(MultipartFile image, Product product);

    void delete(List<String> imageUrl);
}
