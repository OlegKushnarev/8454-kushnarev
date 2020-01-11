package ru.cft.focusstart.repository.entity;

import ru.cft.focusstart.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityRepository<T extends Entity> {
    void add(T entity);

    Optional<T> getById(Long id);

    List<T> get(String... varargs);

    void update(T entity);
}