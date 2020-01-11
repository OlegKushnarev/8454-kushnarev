package ru.cft.focusstart.service.manufacturer;

import ru.cft.focusstart.api.dto.ManufacturerDto;
import ru.cft.focusstart.entity.Manufacturer;
import ru.cft.focusstart.exception.ObjectNotFoundException;
import ru.cft.focusstart.mapper.ManufacturerMapper;
import ru.cft.focusstart.repository.manufacturer.JdbcManufacturerRepository;
import ru.cft.focusstart.repository.manufacturer.ManufacturerRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.cft.focusstart.service.validation.Validator.*;

public class DefaultManufacturerService implements ManufacturerService {
    private static final DefaultManufacturerService INSTANCE = new DefaultManufacturerService();

    private final ManufacturerRepository manufacturerRepository = JdbcManufacturerRepository.getInstance();

    private final ManufacturerMapper manufacturerMapper = ManufacturerMapper.getInstance();

    private DefaultManufacturerService() {
    }

    public static ManufacturerService getInstance() {
        return INSTANCE;
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
    public List<ManufacturerDto> get(String... varargs) {
        return manufacturerRepository.get(varargs)
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