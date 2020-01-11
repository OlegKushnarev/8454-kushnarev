package ru.cft.focusstart.service.manufacturer;

import ru.cft.focusstart.api.dto.ManufacturerDto;
import ru.cft.focusstart.service.EntityService;

import java.util.List;

public interface ManufacturerService extends EntityService<ManufacturerDto> {
    List<ManufacturerDto> getByCategoryId(Long id);
}