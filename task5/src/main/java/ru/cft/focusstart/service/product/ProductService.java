package ru.cft.focusstart.service.product;

import ru.cft.focusstart.api.dto.ProductDto;
import ru.cft.focusstart.service.EntityService;

import java.util.List;

public interface ProductService extends EntityService<ProductDto> {
    List<ProductDto> getByManufacturerId(Long id);

    List<ProductDto> getByCategoryId(Long id);

    void delete(Long id);
}