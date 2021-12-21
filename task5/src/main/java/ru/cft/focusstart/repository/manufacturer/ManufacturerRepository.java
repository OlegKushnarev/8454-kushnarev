package ru.cft.focusstart.repository.manufacturer;

import ru.cft.focusstart.entity.Manufacturer;
import ru.cft.focusstart.repository.entity.EntityRepository;

import java.util.List;

public interface ManufacturerRepository extends EntityRepository<Manufacturer> {
    List<Manufacturer> get(String manufacturerTitle);

    List<Manufacturer> getByCategoryId(Long id);
}