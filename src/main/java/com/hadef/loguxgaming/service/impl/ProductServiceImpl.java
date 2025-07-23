package com.hadef.loguxgaming.service.impl;

import com.hadef.loguxgaming.domain.dto.CreateProductRequest;
import com.hadef.loguxgaming.domain.entity.Genre;
import com.hadef.loguxgaming.domain.entity.Product;
import com.hadef.loguxgaming.domain.entity.Tag;
import com.hadef.loguxgaming.domain.entity.User;
import com.hadef.loguxgaming.domain.value.ProductStatus;
import com.hadef.loguxgaming.repository.ProductRepository;
import com.hadef.loguxgaming.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final TagService tagService;
    private final GenreService genreService;
    private final FileService fileService;

    @Value("${project.poster}")
    private String path;

    @Override
    public Product createProduct(CreateProductRequest createProductRequest,
                                 MultipartFile image, User user) throws IOException {
        if(Files.exists(Path.of(path+ File.separator+image.getOriginalFilename()))){
            throw new RuntimeException("File is already exist with name "+image.getOriginalFilename());
        }
        String uploadedFileName = fileService.uploadFile(path, image);
        List<Tag> tagsByIds = tagService.getTagsByIds(createProductRequest.getTagsIds());
        List<Genre> genresByIds = genreService.getGenresByIds(createProductRequest.getGenresIds());
        Product product = Product.builder()
                .name(createProductRequest.getName())
                .description(createProductRequest.getDescription())
                .price(createProductRequest.getPrice())
                .quantity(createProductRequest.getQuantity())
                .image(uploadedFileName)
                .tags(new HashSet<>(tagsByIds))
                .genres(new HashSet<>(genresByIds))
                .status(createProductRequest.getStatus())
                .user(user)
                .build();
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(UUID genreId, UUID tagId) {
        if(genreId != null && tagId != null) {
            Genre genre = genreService.findById(genreId);
            Tag tag = tagService.findById(tagId);
            return productRepository.findAllByStatusAndGenresContainingAndTagsContaining(
                    ProductStatus.AVAILABLE,
                    genre,
                    tag);
        }
        if(genreId != null) {
            Genre genre = genreService.findById(genreId);
            return productRepository.findAllByStatusAndGenresContaining(
                    ProductStatus.AVAILABLE,
                    genre
            );
        }
        if(tagId != null) {
            Tag tag = tagService.findById(tagId);
            return productRepository.findAllByStatusAndTagsContaining(
                    ProductStatus.AVAILABLE,tag);
        }
        List<Product> allByStatus = productRepository.findAllByStatus(ProductStatus.AVAILABLE);
        return allByStatus;
    }

    @Override
    @Transactional
    public Product findById(UUID id) {
        return productRepository.findByIdWithGenresAndTags(id).orElseThrow(
                ()->new RuntimeException("Product not found with id " + id)
        );
    }

    @Override
    public Product updateProduct(Product product, UUID id) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        productRepository.findById(id).ifPresent(
                product -> {
                    try {
                        fileService.deleteFile(path,product.getImage());
                    } catch (FileNotFoundException e) {
                        log.warn("Couldn't find a file with name: "+product.getImage());
                    }
                    productRepository.deleteById(id);
                }
        );
    }

}
