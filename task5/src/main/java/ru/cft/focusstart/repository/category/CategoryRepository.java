package ru.cft.focusstart.repository.category;

import ru.cft.focusstart.entity.Category;
import ru.cft.focusstart.repository.entity.EntityRepository;

import java.util.List;

public interface CategoryRepository extends EntityRepository<Category> {
    List<Category> get(String categoryTitle);

    List<Category> getByManufacturerId(Long id);
}