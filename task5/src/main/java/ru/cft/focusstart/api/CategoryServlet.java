package ru.cft.focusstart.api;

import ru.cft.focusstart.api.dto.CategoryDto;
import ru.cft.focusstart.api.dto.ManufacturerDto;
import ru.cft.focusstart.api.dto.ProductDto;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.cft.focusstart.api.PathParser.getPathPart;

@WebServlet(urlPatterns = "/categories/*")
public class CategoryServlet extends EntityServlet {

    private static final String CATEGORIES_PATTERN = "^/categories$";
    private static final String CATEGORY_PATTERN = "^/categories/(?<id>[0-9]+)$";
    private static final String CATEGORY_MANUFACTURERS_PATTERN = "^/categories/(?<id>[0-9]+)/manufacturers$";
    private static final String CATEGORY_PRODUCTS_PATTERN = "^/categories/(?<id>[0-9]+)/products$";

    @Override
    protected Operation getOperation(String methodName, String path) {
        Operation operation = super.getOperation(methodName, path);

        if (operation == null) {
            if (methodName.matches("GET")) {
                if (path.matches(CATEGORY_MANUFACTURERS_PATTERN)) {
                    operation = this::getManufacturers;
                } else if (path.matches(CATEGORY_PRODUCTS_PATTERN)) {
                    operation = this::getProducts;
                }
            }
        }
        return operation;
    }

    @Override
    protected String entityPathPattern() {
        return CATEGORIES_PATTERN;
    }

    @Override
    protected String entityByIdPathPattern() {
        return CATEGORY_PATTERN;
    }

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        List<CategoryDto> response = categoryService.get(title);
        writeResp(resp, response);
    }

    @Override
    protected void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryDto response = getCategoryDto(req, CATEGORY_PATTERN);
        writeResp(resp, response);
    }

    @Override
    protected void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryDto request = mapper.readValue(req.getInputStream(), CategoryDto.class);
        CategoryDto response = categoryService.create(request);
        writeResp(resp, response);
    }

    @Override
    protected void merge(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), CATEGORY_PATTERN, "id");
        CategoryDto request = mapper.readValue(req.getInputStream(), CategoryDto.class);

        CategoryDto response = categoryService.merge(id, request);
        writeResp(resp, response);
    }

    @Override
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    private void getManufacturers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryDto categoryDto = getCategoryDto(req, CATEGORY_MANUFACTURERS_PATTERN);
        List<ManufacturerDto> response = manufacturerService.getByCategoryId(categoryDto.getId());
        writeResp(resp, response);
    }

    private void getProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryDto categoryDto = getCategoryDto(req, CATEGORY_PRODUCTS_PATTERN);
        List<ProductDto> response = productService.getByCategoryId(categoryDto.getId());
        writeResp(resp, response);
    }

    private CategoryDto getCategoryDto(HttpServletRequest req, String pattern) {
        Long id = getPathPart(getPath(req), pattern, "id");
        return categoryService.getById(id);
    }
}