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

@WebServlet(urlPatterns = "/manufacturers/*")
public class ManufacturerServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    private static final String MANUFACTURERS_PATTERN = "^/manufacturers$";
    private static final String MANUFACTURER_PATTERN = "^/manufacturers/(?<id>[0-9]+)$";
    private static final String MANUFACTURER_PRODUCTS_PATTERN = "^/manufacturers/(?<id>[0-9]+)/products$";
    private static final String MANUFACTURER_CATEGORIES_PATTERN = "^/manufacturers/(?<id>[0-9]+)/categories$";

    private final ObjectMapper mapper = new ObjectMapper();

    private final ManufacturerService manufacturerService = DefaultManufacturerService.getInstance();

    private final ProductService productService = DefaultProductService.getInstance();

    private final CategoryService categoryService = DefaultCategoryService.getInstance();

    private final ExceptionHandler exceptionHandler = ExceptionHandler.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String path = getPath(req);
            if (path.matches(MANUFACTURERS_PATTERN)) {
                get(req, resp);
            } else if (path.matches(MANUFACTURER_PATTERN)) {
                getById(req, resp);
            } else if (path.matches(MANUFACTURER_PRODUCTS_PATTERN)) {
                getProducts(req, resp);
            } else if (path.matches(MANUFACTURER_CATEGORIES_PATTERN)) {
                getCategories(req, resp);
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
            if (path.matches(MANUFACTURERS_PATTERN)) {
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
            if (path.matches(MANUFACTURER_PATTERN)) {
                merge(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            exceptionHandler.handleExceptions(e, resp);
        }
    }

    private String getPath(HttpServletRequest req) {
        return req.getRequestURI().substring(req.getContextPath().length());
    }

    private void merge(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), MANUFACTURER_PATTERN, "id");
        ManufacturerDto request = mapper.readValue(req.getInputStream(), ManufacturerDto.class);

        ManufacturerDto response = manufacturerService.merge(id, request);
        writeResp(resp, response);
    }

    private void writeResp(HttpServletResponse resp, Object response) throws IOException {
        resp.setContentType("application/json");
        mapper.writeValue(resp.getOutputStream(), response);
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("title");
        log.info("Name: {}", name);
        List<ManufacturerDto> response = manufacturerService.get(name);
        writeResp(resp, response);
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), MANUFACTURER_PATTERN, "id");
        log.info("Id: {}", id);

        ManufacturerDto response = manufacturerService.getById(id);
        writeResp(resp, response);
    }

    private void getProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), MANUFACTURER_PRODUCTS_PATTERN, "id");
        log.info("Id: {}", id);

        List<ProductDto> response = productService.getByManufacturerId(id);
        writeResp(resp, response);
    }

    private void getCategories(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), MANUFACTURER_CATEGORIES_PATTERN, "id");
        log.info("Id: {}", id);

        List<CategoryDto> response = categoryService.getByManufacturerId(id);
        writeResp(resp, response);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ManufacturerDto request = mapper.readValue(req.getInputStream(), ManufacturerDto.class);

        ManufacturerDto response = manufacturerService.create(request);
        writeResp(resp, response);
    }
}