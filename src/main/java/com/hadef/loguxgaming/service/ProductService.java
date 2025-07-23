package com.hadef.loguxgaming.service;

import com.hadef.loguxgaming.domain.dto.CreateProductRequest;
import com.hadef.loguxgaming.domain.dto.UpdateProductRequest;
import com.hadef.loguxgaming.domain.entity.Product;
import com.hadef.loguxgaming.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(CreateProductRequest createProductRequest,
                          MultipartFile image, User user) throws IOException;
    Product updateProduct(UUID id, UpdateProductRequest updateProductRequest,
                          User user, MultipartFile image) throws IOException;
    List<Product> getAllProducts(UUID genreId, UUID tagId);
    Product findById(UUID id);
    void deleteById(UUID id);
}
