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

@WebServlet(urlPatterns = "/manufacturers/*")
public class ManufacturerServlet extends EntityServlet {

    private static final String MANUFACTURERS_PATTERN = "^/manufacturers$";
    private static final String MANUFACTURER_PATTERN = "^/manufacturers/(?<id>[0-9]+)$";
    private static final String MANUFACTURER_PRODUCTS_PATTERN = "^/manufacturers/(?<id>[0-9]+)/products$";
    private static final String MANUFACTURER_CATEGORIES_PATTERN = "^/manufacturers/(?<id>[0-9]+)/categories$";

    @Override
    protected Operation getOperation(String methodName, String path) {
        Operation operation = super.getOperation(methodName, path);

        if (operation == null) {
            if (methodName.matches("GET")) {
                if (path.matches(MANUFACTURER_PRODUCTS_PATTERN)) {
                    operation = this::getProducts;
                } else if (path.matches(MANUFACTURER_CATEGORIES_PATTERN)) {
                    operation = this::getCategories;
                }
            }
        }
        return operation;
    }

    @Override
    protected String entityPathPattern() {
        return MANUFACTURERS_PATTERN;
    }

    @Override
    protected String entityByIdPathPattern() {
        return MANUFACTURER_PATTERN;
    }

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        List<ManufacturerDto> response = manufacturerService.get(title);
        writeResp(resp, response);
    }

    @Override
    protected void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ManufacturerDto response = getManufacturerDto(req, MANUFACTURER_PATTERN);
        writeResp(resp, response);
    }

    @Override
    protected void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ManufacturerDto request = mapper.readValue(req.getInputStream(), ManufacturerDto.class);
        ManufacturerDto response = manufacturerService.create(request);
        writeResp(resp, response);
    }

    @Override
    protected void merge(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), MANUFACTURER_PATTERN, "id");
        ManufacturerDto request = this.mapper.readValue(req.getInputStream(), ManufacturerDto.class);
        ManufacturerDto response = manufacturerService.merge(id, request);
        writeResp(resp, response);
    }

    @Override
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    private void getProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ManufacturerDto manufacturerDto = getManufacturerDto(req, MANUFACTURER_PRODUCTS_PATTERN);
        List<ProductDto> response = productService.getByManufacturerId(manufacturerDto.getId());
        writeResp(resp, response);
    }

    private void getCategories(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ManufacturerDto manufacturerDto = getManufacturerDto(req, MANUFACTURER_CATEGORIES_PATTERN);
        List<CategoryDto> response = categoryService.getByManufacturerId(manufacturerDto.getId());
        writeResp(resp, response);
    }

    private ManufacturerDto getManufacturerDto(HttpServletRequest req, String pattern) {
        Long id = getPathPart(getPath(req), pattern, "id");
        return manufacturerService.getById(id);
    }
}