package ru.cft.focusstart.service.manufacturer;

import org.springframework.stereotype.Service;
import ru.cft.focusstart.api.dto.ManufacturerDto;
import ru.cft.focusstart.entity.Manufacturer;
import ru.cft.focusstart.exception.ObjectNotFoundException;
import ru.cft.focusstart.mapper.ManufacturerMapper;
import ru.cft.focusstart.repository.manufacturer.ManufacturerRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.cft.focusstart.service.validation.Validator.*;

@Service
public class DefaultManufacturerService implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    private final ManufacturerMapper manufacturerMapper;

    private DefaultManufacturerService(
            ManufacturerRepository manufacturerRepository,
            ManufacturerMapper manufacturerMapper
    ) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerMapper = manufacturerMapper;
    }

    @Override
    public ManufacturerDto create(ManufacturerDto manufacturerDto) {
        validate(manufacturerDto);
        Manufacturer manufacturer = add(null, manufacturerDto);
        return manufacturerMapper.toDto(manufacturer);
    }

    @Override
    public ManufacturerDto getById(Long id) {
        checkNotNull("id", id);
        Manufacturer manufacturer = getManufacturer(id);
        return manufacturerMapper.toDto(manufacturer);
    }

    @Override
    public List<ManufacturerDto> get(String manufacturerTitle) {
        return manufacturerRepository.get(manufacturerTitle)
                .stream()
                .map(manufacturerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ManufacturerDto> getByCategoryId(Long id) {
        return manufacturerRepository.getByCategoryId(id)
                .stream()
                .map(manufacturerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ManufacturerDto merge(Long id, ManufacturerDto manufacturerDto) {
        checkNotNull("id", id);
        validate(manufacturerDto);

        Manufacturer manufacturer = manufacturerRepository.getById(id)
                .map(existing -> update(existing, manufacturerDto))
                .orElseGet(() -> add(id, manufacturerDto));

        return manufacturerMapper.toDto(manufacturer);
    }

    private void validate(ManufacturerDto manufacturerDto) {
        checkNull("manufacturer.id", manufacturerDto.getId());
        checkSize("manufacturer.title", manufacturerDto.getTitle(), 1, 50);
    }

    private Manufacturer add(Long id, ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(id);
        manufacturer.setTitle(manufacturerDto.getTitle());
        manufacturerRepository.add(manufacturer);
        return manufacturer;
    }

    private Manufacturer getManufacturer(Long id) {
        return manufacturerRepository.getById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Manufacturer with id %s not found", id)));
    }

    private Manufacturer update(Manufacturer manufacturer, ManufacturerDto manufacturerDto) {
        manufacturer.setTitle(manufacturerDto.getTitle());
        manufacturerRepository.update(manufacturer);
        return manufacturer;
    }
}