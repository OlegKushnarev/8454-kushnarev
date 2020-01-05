package ru.cft.focusstart.repository.manufacturer;

import ru.cft.focusstart.entity.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerRepository {
    void add(Manufacturer manufacturer);

    Optional<Manufacturer> getById(Long id);

    List<Manufacturer> get(String name);

    List<Manufacturer> getByCategoryId(Long id);

    void update(Manufacturer manufacturer);
}