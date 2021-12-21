package ru.cft.focusstart.repository.reader;

import ru.cft.focusstart.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductReader {
    private ProductReader() {
    }

    public static Product readProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setTitle(rs.getString("title"));
        product.setVendorCode(rs.getString("vendorCode"));
        product.setDescription(rs.getString("description"));

        return product;
    }
}