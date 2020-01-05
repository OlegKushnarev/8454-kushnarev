package ru.cft.focusstart.mapper;

import ru.cft.focusstart.api.dto.ProductDto;
import ru.cft.focusstart.entity.Product;

public class ProductMapper {
    private static final ProductMapper INSTANCE = new ProductMapper();

    private ProductMapper() {
    }

    public static ProductMapper getInstance() {
        return INSTANCE;
    }

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