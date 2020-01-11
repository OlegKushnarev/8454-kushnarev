package ru.cft.focusstart.service.category;

import ru.cft.focusstart.api.dto.CategoryDto;
import ru.cft.focusstart.service.EntityService;

import java.util.List;

public interface CategoryService extends EntityService<CategoryDto> {
    List<CategoryDto> getByManufacturerId(Long id);
}