package com.hadef.loguxgaming.mapper;

import com.hadef.loguxgaming.domain.dto.TagDto;
import com.hadef.loguxgaming.domain.entity.Product;
import com.hadef.loguxgaming.domain.entity.Tag;
import com.hadef.loguxgaming.domain.value.ProductStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    @Mapping(target = "productCount",source = "products", qualifiedByName = "calculateProductCount")
    TagDto toDto(Tag tag);
    Tag toEntity(TagDto tagDto);

    @Named("calculateProductCount")
    default Integer calculatePostCount(Set<Product> products) {
        if(products==null){
            return 0;
        }
        return (int) products.stream().filter(
                product -> product.getStatus() == ProductStatus.AVAILABLE
        ).count();
    }
}
