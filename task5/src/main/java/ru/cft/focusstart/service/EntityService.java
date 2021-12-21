package ru.cft.focusstart.service;

import ru.cft.focusstart.api.dto.EntityDto;

public interface EntityService<T extends EntityDto> {
    T create(T entityDto);

    T getById(Long id);

    T merge(Long id, T entityDto);
}