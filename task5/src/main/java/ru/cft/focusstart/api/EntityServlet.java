package ru.cft.focusstart.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import ru.cft.focusstart.service.category.CategoryService;
import ru.cft.focusstart.service.manufacturer.ManufacturerService;
import ru.cft.focusstart.service.product.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class EntityServlet extends HttpServlet {
    protected final ObjectMapper mapper = new ObjectMapper();

    protected ManufacturerService manufacturerService;

    protected ProductService productService;

    protected CategoryService categoryService;

    protected ExceptionHandler exceptionHandler;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ApplicationContext applicationContext =
                (ApplicationContext) config.getServletContext().getAttribute("applicationContext");

        this.manufacturerService = applicationContext.getBean(ManufacturerService.class);
        this.productService = applicationContext.getBean(ProductService.class);
        this.categoryService = applicationContext.getBean(CategoryService.class);
        this.exceptionHandler = applicationContext.getBean(ExceptionHandler.class);
    }


    protected String getPath(HttpServletRequest req) {
        return req.getRequestURI().substring(req.getContextPath().length());
    }

    protected void writeResp(HttpServletResponse resp, Object response) throws IOException {
        resp.setContentType("application/json");
        mapper.writeValue(resp.getOutputStream(), response);
    }

    abstract protected String entityPathPattern();

    abstract protected String entityByIdPathPattern();

    abstract protected void get(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    abstract protected void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    abstract protected void create(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    abstract protected void merge(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    abstract protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    protected Operation getOperation(String methodName, String path) {
        if (path.matches(entityPathPattern())) {
            if (methodName.matches("GET")) {
                return this::get;
            }
            if (methodName.matches("POST")) {
                return this::create;
            }
        } else if (path.matches(entityByIdPathPattern())) {
            if (methodName.matches("GET")) {
                return this::getById;
            }
            if (methodName.matches("DELETE")) {
                return this::delete;
            }
            if (methodName.matches("PUT")) {
                return this::merge;
            }
        }
        return null;
    }

    private void doMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Operation operation = getOperation(req.getMethod(), getPath(req));
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