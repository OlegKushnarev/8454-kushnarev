package ru.cft.focusstart.mapper;

import ru.cft.focusstart.api.dto.ManufacturerDto;
import ru.cft.focusstart.entity.Category;
import ru.cft.focusstart.entity.Manufacturer;

public class ManufacturerMapper {
    private static final ManufacturerMapper INSTANCE = new ManufacturerMapper();

    private ManufacturerMapper() {
    }

    public static ManufacturerMapper getInstance() {
        return INSTANCE;
    }

    public ManufacturerDto toDto(Manufacturer manufacturer) {
        return ManufacturerDto.builder()
                .id(manufacturer.getId())
                .title(manufacturer.getTitle())
                .build();
    }
}