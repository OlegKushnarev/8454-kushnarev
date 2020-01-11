package ru.cft.focusstart.mapper;

import ru.cft.focusstart.api.dto.CategoryDto;
import ru.cft.focusstart.entity.Category;

public class CategoryMapper {
    private static final CategoryMapper INSTANCE = new CategoryMapper();

    private CategoryMapper() {
    }

    public static CategoryMapper getInstance() {
        return INSTANCE;
    }

    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .build();
    }
}