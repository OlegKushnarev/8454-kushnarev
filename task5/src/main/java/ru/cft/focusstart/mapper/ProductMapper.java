package ru.cft.focusstart.mapper;

import org.springframework.stereotype.Component;
import ru.cft.focusstart.api.dto.ProductDto;
import ru.cft.focusstart.entity.Product;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .categoryId(product.getCategory().getId())
                .manufacturerId(product.getManufacturer().getId())
                .vendorCode(product.getVendorCode())
                .description(product.getDescription())
                .build();
    }
}