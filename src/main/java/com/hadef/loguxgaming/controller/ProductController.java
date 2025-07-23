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
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ProductDto> createProduct(@RequestPart String createProductDto
            , @RequestPart MultipartFile image
            , @RequestHeader UUID userId
    ) throws IOException {
        if(image.isEmpty()) image=null;
        CreateProductDto dtoProduct = convertToCreateProductDto(createProductDto);
        User loggedInUser = userService.getUserById(userId);
        CreateProductRequest createProductRequest = productMapper.toCreateProductRequest(dtoProduct);
        Product product = productService.createProduct(createProductRequest, image, loggedInUser);
        ProductDto dto = productMapper.toDto(product);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        Product byId = productService.findById(id);
        ProductDto dto = productMapper.toDto(byId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/view/{id}")
    public ResponseEntity<ViewProductDto> getViewProductById(@PathVariable UUID id) {
        Product byId = productService.findById(id);
        ViewProductDto dto = productMapper.toViewDto(byId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable UUID id,
                                                        @RequestPart String updateProductDto,
                                                        @RequestPart(required = false) MultipartFile image,
                                                        @RequestHeader UUID userId) throws IOException {
        User loggedInUser = userService.getUserById(userId);
        UpdateProductDto convertedDto = convertToUpdateProductDto(updateProductDto);
        UpdateProductRequest updateProductRequest = productMapper.toUpdateProductRequest(convertedDto);
        Product product = productService.updateProduct(id, updateProductRequest, loggedInUser, image);
        ProductDto dto = productMapper.toDto(product);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private CreateProductDto convertToCreateProductDto(String dtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(dtoObj, CreateProductDto.class);
    }

    private UpdateProductDto convertToUpdateProductDto(String dtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(dtoObj, UpdateProductDto.class);
    }
}
