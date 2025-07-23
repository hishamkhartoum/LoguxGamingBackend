package com.hadef.loguxgaming.mapper;

import com.hadef.loguxgaming.domain.dto.CreateProductDto;
import com.hadef.loguxgaming.domain.dto.CreateProductRequest;
import com.hadef.loguxgaming.domain.dto.ProductDto;
import com.hadef.loguxgaming.domain.dto.ViewProductDto;
import com.hadef.loguxgaming.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "user",source = "user")
    @Mapping(target = "genres",source = "genres")
    @Mapping(target = "tags", source = "tags")
    ProductDto toDto(Product product);
    @Mapping(target = "genres",source = "genres")
    @Mapping(target = "tags", source = "tags")
    ViewProductDto toViewDto(Product product);

    CreateProductRequest toCreateProductRequest(CreateProductDto createProductDto);
}
