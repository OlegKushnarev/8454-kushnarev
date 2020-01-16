package ru.cft.focusstart.service.product;

import ru.cft.focusstart.api.dto.ProductDto;
import ru.cft.focusstart.entity.Category;
import ru.cft.focusstart.entity.Manufacturer;
import ru.cft.focusstart.entity.Product;
import ru.cft.focusstart.exception.InvalidParametersException;
import ru.cft.focusstart.exception.ObjectNotFoundException;
import ru.cft.focusstart.mapper.ProductMapper;
import ru.cft.focusstart.repository.category.CategoryRepository;
import ru.cft.focusstart.repository.category.JdbcCategoryRepository;
import ru.cft.focusstart.repository.manufacturer.JdbcManufacturerRepository;
import ru.cft.focusstart.repository.manufacturer.ManufacturerRepository;
import ru.cft.focusstart.repository.product.JdbcProductRepository;
import ru.cft.focusstart.repository.product.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.cft.focusstart.service.validation.Validator.*;

public class DefaultProductService implements ProductService {
    private static final DefaultProductService INSTANCE = new DefaultProductService();

    private final CategoryRepository categoryRepository = JdbcCategoryRepository.getInstance();

    private final ManufacturerRepository manufacturerRepository = JdbcManufacturerRepository.getInstance();

    private final ProductRepository productRepository = JdbcProductRepository.getInstance();

    private final ProductMapper productMapper = ProductMapper.getInstance();

    private DefaultProductService() {
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        validate(productDto);
        Product product = add(null, productDto);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto getById(Long id) {
        checkNotNull("id", id);
        Product product = getProduct(id);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> get(String productTitle, String manufacturerTitle) {
        return productRepository.get(productTitle, manufacturerTitle)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getByManufacturerId(Long id) {
        return productRepository.getByManufacturerId(id)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getByCategoryId(Long id) {
        return productRepository.getByCategoryId(id)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto merge(Long id, ProductDto bookDto) {
        checkNotNull("id", id);
        validate(bookDto);

        Product product = productRepository.getById(id)
                .map(existing -> update(existing, bookDto))
                .orElseGet(() -> add(id, bookDto));

        return productMapper.toDto(product);
    }

    @Override
    public void delete(Long id) {
        checkNotNull("id", id);
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    private void validate(ProductDto productDto) {
        checkNull("product.id", productDto.getId());
        checkSize("product.title", productDto.getTitle(), 1, 50);
        checkNotNull("product.categoryId", productDto.getCategoryId());
        checkSize("product.vendorCode", productDto.getVendorCode(), 1, 50);
        checkNotNull("product.manufacturerId", productDto.getManufacturerId());
        checkSize("product.description", productDto.getDescription(), 1, 100);
    }

    private Product add(Long id, ProductDto productDto) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setVendorCode(productDto.getVendorCode());

        Manufacturer manufacturer = getManufacturer(productDto.getManufacturerId());
        product.setManufacturer(manufacturer);

        Category category = getCategory(productDto.getCategoryId());
        product.setCategory(category);

        productRepository.add(product);

        return product;
    }

    private Manufacturer getManufacturer(Long id) {
        return manufacturerRepository.getById(id)
                .orElseThrow(() -> new InvalidParametersException(String.format("Manufacturer with id %s not found", id)));
    }

    private Category getCategory(Long id) {
        return categoryRepository.getById(id)
                .orElseThrow(() -> new InvalidParametersException(String.format("Category with id %s not found", id)));
    }

    private Product getProduct(Long id) {
        return productRepository.getById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Product with id %s not found", id)));
    }

    private Product update(Product product, ProductDto productDto) {
        product.setTitle(productDto.getTitle());
        product.setVendorCode(productDto.getVendorCode());
        product.setDescription(productDto.getDescription());

        if (!productDto.getCategoryId().equals(product.getCategory().getId())) {
            Category newCategory = getCategory(productDto.getCategoryId());
            product.setCategory(newCategory);
        }

        if (!productDto.getManufacturerId().equals(product.getManufacturer().getId())) {
            Manufacturer newManufacturer = getManufacturer(productDto.getManufacturerId());
            product.setManufacturer(newManufacturer);
        }

        productRepository.update(product);

        return product;
    }
}