package ru.cft.focusstart.service.manufacturer;

import ru.cft.focusstart.api.dto.ManufacturerDto;

import java.util.List;

public interface ManufacturerService {
    ManufacturerDto create(ManufacturerDto manufacturerDto);

    ManufacturerDto getById(Long id);

    List<ManufacturerDto> get(String name);

    List<ManufacturerDto> getByCategoryId(Long id);

    ManufacturerDto merge(Long id, ManufacturerDto manufacturerDto);
}