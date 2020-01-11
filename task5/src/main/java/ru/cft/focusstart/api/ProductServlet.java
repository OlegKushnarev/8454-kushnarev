package ru.cft.focusstart.api;

import ru.cft.focusstart.api.dto.ProductDto;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.cft.focusstart.api.PathParser.getPathPart;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends EntityServlet {
    private static final String PRODUCTS_PATTERN = "^/products$";
    private static final String PRODUCT_PATTERN = "^/products/(?<id>[0-9]+)$";

    @Override
    protected String getPattern() {
        return PRODUCTS_PATTERN;
    }

    @Override
    protected String getByIdPattern() {
        return PRODUCT_PATTERN;
    }

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String manufacturerTitle = req.getParameter("manufacturerTitle");

        List<ProductDto> response = productService.get(title, manufacturerTitle);
        writeResp(resp, response);
    }

    @Override
    protected void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), PRODUCT_PATTERN, "id");
        ProductDto response = productService.getById(id);
        writeResp(resp, response);
    }

    @Override
    protected void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductDto request = mapper.readValue(req.getInputStream(), ProductDto.class);
        ProductDto response = productService.create(request);
        writeResp(resp, response);
    }

    @Override
    protected void merge(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), PRODUCT_PATTERN, "id");
        ProductDto request = mapper.readValue(req.getInputStream(), ProductDto.class);
        ProductDto response = productService.merge(id, request);
        writeResp(resp, response);
    }

    @Override
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getPathPart(getPath(req), PRODUCT_PATTERN, "id");
        productService.delete(id);
    }
}
