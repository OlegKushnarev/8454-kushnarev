package ru.cft.focusstart.repository.category;

import ru.cft.focusstart.entity.Category;
import ru.cft.focusstart.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    void add(Category category);

    Optional<Category> getById(Long id);

    List<Category> get(String name);

    List<Category> getByManufacturerId(Long id);

    void update(Category category);
}