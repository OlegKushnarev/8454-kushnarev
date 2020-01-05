package ru.cft.focusstart.repository.reader;

import ru.cft.focusstart.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryReader {
    private CategoryReader() {
    }

    public static Category readCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("category.id"));
        category.setName(rs.getString("category.name"));

        return category;
    }
}