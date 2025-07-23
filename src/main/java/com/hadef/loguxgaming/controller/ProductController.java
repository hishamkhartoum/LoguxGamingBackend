package com.hadef.loguxgaming.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadef.loguxgaming.domain.dto.*;
import com.hadef.loguxgaming.domain.entity.Product;
import com.hadef.loguxgaming.domain.entity.User;
import com.hadef.loguxgaming.mapper.ProductMapper;
import com.hadef.loguxgaming.service.ProductService;
import com.hadef.loguxgaming.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam(required = false) UUID genreId,
            @RequestParam(required = false) UUID tagId
            ) {
        List<ProductDto> allProducts = productService.getAllProducts(genreId, tagId)
                .stream().map(productMapper::toDto).toList();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity<ViewProductDto> createProduct(@RequestPart String createProductDto
            , @RequestPart(required = false) MultipartFile image
            , @RequestHeader UUID userId
    ) throws IOException {
        if(image.isEmpty()) image=null;
        CreateProductDto dtoProduct = convertToCreateProductDto(createProductDto);
        User loggedInUser = userService.getUserById(userId);
        CreateProductRequest createProductRequest = productMapper.toCreateProductRequest(dtoProduct);
        Product product = productService.createProduct(createProductRequest, image, loggedInUser);
        ViewProductDto dto = productMapper.toViewDto(product);
        return ResponseEntity.ok(dto);
    }

    private CreateProductDto convertToCreateProductDto(String dtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(dtoObj, CreateProductDto.class);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ViewProductDto> getProductById(@PathVariable UUID id) {
        Product byId = productService.findById(id);
        ViewProductDto dto = productMapper.toViewDto(byId);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
