package ru.cft.focusstart.service.category;

import ru.cft.focusstart.api.dto.CategoryDto;
import ru.cft.focusstart.api.dto.ProductDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto);

    CategoryDto getById(Long id);

    List<CategoryDto> get(String name);

    List<CategoryDto> getByManufacturerId(Long id);

  //  List<BookDto> getBooks(Long id);

    CategoryDto merge(Long id, CategoryDto categoryDto);
}