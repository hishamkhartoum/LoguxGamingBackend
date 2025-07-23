package com.hadef.loguxgaming.mapper;

import com.hadef.loguxgaming.domain.dto.GenreDto;
import com.hadef.loguxgaming.domain.entity.Genre;
import com.hadef.loguxgaming.domain.entity.Product;
import com.hadef.loguxgaming.domain.value.ProductStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenreMapper {
    @Mapping(target = "productsCount",source = "products",qualifiedByName = "calculateProductCount")
    GenreDto toDto(Genre genre);

    @Named("calculateProductCount")
    default Integer calculateProductCount(Set<Product> products) {
        if(products==null){
            return 0;
        }
        return (int) products.stream().filter(
                product->product
                        .getStatus()== ProductStatus.AVAILABLE).count();
    }

}
