package ru.cft.focusstart.repository.reader;

import ru.cft.focusstart.entity.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerReader {
    private ManufacturerReader() {
    }

    public static Manufacturer readManufacturer(ResultSet rs) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(rs.getLong("manufecture.id"));
        manufacturer.setTitle(rs.getString("manufecture.title"));

        return manufacturer;
    }
}