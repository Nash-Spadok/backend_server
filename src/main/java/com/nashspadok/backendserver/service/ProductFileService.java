package com.nashspadok.backendserver.service;

import com.nashspadok.backendserver.model.file.ProductFile;
import com.nashspadok.backendserver.model.product.Product;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ProductFileService {
    ProductFile create(MultipartFile image, Product product);

    void delete(List<String> imageUrl);
}
