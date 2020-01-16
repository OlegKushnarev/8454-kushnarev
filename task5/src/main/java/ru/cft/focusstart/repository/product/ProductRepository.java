package ru.cft.focusstart.repository.product;

import ru.cft.focusstart.entity.Product;
import ru.cft.focusstart.repository.entity.EntityRepository;

import java.util.List;

public interface ProductRepository extends EntityRepository<Product> {
    List<Product> get(String productTitle, String manufacturerTitle);

    List<Product> getByManufacturerId(Long id);

    List<Product> getByCategoryId(Long id);

    void delete(Product product);
}