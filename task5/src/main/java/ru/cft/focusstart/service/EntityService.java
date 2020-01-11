package ru.cft.focusstart.service;

import ru.cft.focusstart.api.dto.EntityDto;

import java.util.List;

public interface EntityService<T extends EntityDto> {
    T create(T entityDto);

    T getById(Long id);

    List<T> get(String... varargs);

    T merge(Long id, T entityDto);
}