package ru.cft.focusstart.mapper;

import org.springframework.stereotype.Component;
import ru.cft.focusstart.api.dto.ManufacturerDto;
import ru.cft.focusstart.entity.Manufacturer;

@Component
public class ManufacturerMapper {

    public ManufacturerDto toDto(Manufacturer manufacturer) {
        return ManufacturerDto.builder()
                .id(manufacturer.getId())
                .title(manufacturer.getTitle())
                .build();
    }
}