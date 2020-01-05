package ru.cft.focusstart.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.api.dto.CategoryDto;
import ru.cft.focusstart.api.dto.ManufacturerDto;
import ru.cft.focusstart.api.dto.ProductDto;
import ru.cft.focusstart.api.logging.LoggingFilter;
import ru.cft.focusstart.service.category.CategoryService;
import ru.cft.focusstart.service.category.DefaultCategoryService;
import ru.cft.focusstart.service.manufacturer.DefaultManufacturerService;
import ru.cft.focusstart.service.manufacturer.ManufacturerService;
import ru.cft.focusstart.service.product.DefaultProductService;
import ru.cft.focusstart.service.product.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.cft.focusstart.api.PathParser.getPathPart;

@WebServlet(urlPatterns = "/categories/*")
public class CategoryServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    private static final String CATEGORIES_PATTERN = "^/categories$";
    private static final String CATEGORY_PATTERN = "^/categories/(?<id>[0-9]+)$";
    private static final String CATEGORY_MANUFACTURERS_PATTERN = "^/categories/(?<id>[0-9]+)/manufacturers$";
    private static final String CATEGORY_PRODUCTS_PATTERN = "^/categories/(?<id>[0-9]+)/products$";

    private final ObjectMapper mapper = new ObjectMapper();

    private final ExceptionHandler exceptionHandler = ExceptionHandler.getInstance();

    private final CategoryService categoryService = DefaultCategoryService.getInstance();

    private final ProductService productService = DefaultProductService.getInstance();

    private final ManufacturerService manufacturerService = DefaultManufacturerService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String path = getPath(req);
            if (path.matches(CATEGORIES_PATTERN)) {
                get(req, resp);
            } else if (path.matches(CATEGORY_PATTERN)) {
                getById(req, resp);
            } else if (path.matches(CATEGORY_MANUFACTURERS_PATTERN)) {
                getManufacturers(req, resp);
            } else if (path.matches(CATEGORY_PRODUCTS_PATTERN)) {
                getProducts(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            exceptionHandler.handleExceptions(e, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String path = getPath(req);
            if (path.matches(CATEGORIES_PATTERN)) {
                create(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            exceptionHandler.handleExceptions(e, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String path = getPath(req);
            if (path.matches(CATEGORY_PATTERN)) {
                merge(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            exceptionHandler.handleExceptions(e, resp);
        }
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");

        List<CategoryDto> response = categoryService.get(name);
        writeResp(resp, response);
    }

    private void getManufacturers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), CATEGORY_MANUFACTURERS_PATTERN, "id");
        log.info("Id: {}", id);

        List<ManufacturerDto> response = manufacturerService.getByCategoryId(id);
        writeResp(resp, response);
    }

    private void getProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), CATEGORY_PRODUCTS_PATTERN, "id");
        log.info("Id: {}", id);

        List<ProductDto> response = productService.getByCategoryId(id);
        writeResp(resp, response);
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), CATEGORY_PATTERN, "id");

        CategoryDto response = categoryService.getById(id);
        writeResp(resp, response);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryDto request = mapper.readValue(req.getInputStream(), CategoryDto.class);

        CategoryDto response = categoryService.create(request);
        writeResp(resp, response);
    }

    private String getPath(HttpServletRequest req) {
        return req.getRequestURI().substring(req.getContextPath().length());
    }

    private void merge(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), CATEGORY_PATTERN, "id");
        CategoryDto request = mapper.readValue(req.getInputStream(), CategoryDto.class);

        CategoryDto response = categoryService.merge(id, request);
        writeResp(resp, response);
    }

    private void writeResp(HttpServletResponse resp, Object response) throws IOException {
        resp.setContentType("application/json");
        mapper.writeValue(resp.getOutputStream(), response);
    }
}