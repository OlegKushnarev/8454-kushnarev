package ru.cft.focusstart.repository.product;

import ru.cft.focusstart.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void add(Product product);

    Optional<Product> getById(Long id);

    List<Product> get(String name, String manufacturerTitle);

    List<Product> getByManufacturerId(Long id);

    List<Product> getByCategoryId(Long id);

    void update(Product product);

    void delete(Product product);
}