package ru.cft.focusstart.service.category;

import org.springframework.stereotype.Service;
import ru.cft.focusstart.api.dto.CategoryDto;
import ru.cft.focusstart.entity.Category;
import ru.cft.focusstart.exception.ObjectNotFoundException;
import ru.cft.focusstart.mapper.CategoryMapper;
import ru.cft.focusstart.repository.category.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.cft.focusstart.service.validation.Validator.*;

@Service
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private DefaultCategoryService(
            CategoryRepository categoryRepository,
            CategoryMapper categoryMapper
    ) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        validate(categoryDto);

        Category category = add(null, categoryDto);

        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto getById(Long id) {
        checkNotNull("id", id);

        Category category = getCategory(id);

        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> get(String categoryTitle) {
        return categoryRepository.get(categoryTitle)
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getByManufacturerId(Long id) {
        return categoryRepository.getByManufacturerId(id)
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto merge(Long id, CategoryDto categoryDto) {
        checkNotNull("id", id);
        validate(categoryDto);

        Category category = categoryRepository.getById(id)
                .map(existing -> update(existing, categoryDto))
                .orElseGet(() -> add(id, categoryDto));

        return categoryMapper.toDto(category);
    }

    private void validate(CategoryDto categoryDto) {
        checkNull("category.id", categoryDto.getId());
        checkSize("category.title", categoryDto.getTitle(), 1, 30);
    }

    private Category add(Long id, CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(id);
        category.setTitle(categoryDto.getTitle());

        categoryRepository.add(category);

        return category;
    }

    private Category getCategory(Long id) {
        return categoryRepository.getById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Category with id %s not found", id)));
    }

    private Category update(Category category, CategoryDto categoryDto) {
        category.setTitle(categoryDto.getTitle());

        categoryRepository.update(category);

        return category;
    }
}
