package ru.cft.focusstart.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.cft.focusstart.service.category.CategoryService;
import ru.cft.focusstart.service.category.DefaultCategoryService;
import ru.cft.focusstart.service.manufacturer.DefaultManufacturerService;
import ru.cft.focusstart.service.manufacturer.ManufacturerService;
import ru.cft.focusstart.service.product.DefaultProductService;
import ru.cft.focusstart.service.product.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class EntityServlet extends HttpServlet {
    protected final ObjectMapper mapper = new ObjectMapper();

    protected final ManufacturerService manufacturerService = DefaultManufacturerService.getInstance();

    protected final ProductService productService = DefaultProductService.getInstance();

    protected final CategoryService categoryService = DefaultCategoryService.getInstance();

    protected final ExceptionHandler exceptionHandler = ExceptionHandler.getInstance();

    protected String getPath(HttpServletRequest req) {
        return req.getRequestURI().substring(req.getContextPath().length());
    }

    protected void writeResp(HttpServletResponse resp, Object response) throws IOException {
        resp.setContentType("application/json");
        mapper.writeValue(resp.getOutputStream(), response);
    }

    abstract protected String getPattern();

    abstract protected String getByIdPattern();

    abstract protected void get(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    abstract protected void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    abstract protected void create(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    abstract protected void merge(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    abstract protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    protected Operation getOperation(String methodName, String path) {
        if (path.matches(getPattern())) {
            if (methodName.matches("doGet")) {
                return this::get;
            }
            return this::create;
        } else if (path.matches(getByIdPattern())) {
            if (methodName.matches("doGet")) {
                return this::getById;
            }
            if (methodName.matches("doDelete")) {
                return this::delete;
            }
            return this::merge;
        }
        return null;
    }

    private void doMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String path = getPath(req);
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            Operation operation = getOperation(stackTraceElements[2].getMethodName(), path);
            if (operation != null) {
                operation.getResult(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            exceptionHandler.handleExceptions(e, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doMethod(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doMethod(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doMethod(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doMethod(req, resp);
    }
}