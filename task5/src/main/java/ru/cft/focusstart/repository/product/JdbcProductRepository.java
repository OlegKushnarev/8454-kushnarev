package ru.cft.focusstart.repository.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.api.logging.LoggingFilter;
import ru.cft.focusstart.entity.Category;
import ru.cft.focusstart.entity.Manufacturer;
import ru.cft.focusstart.entity.Product;
import ru.cft.focusstart.repository.DataAccessException;
import ru.cft.focusstart.repository.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static ru.cft.focusstart.repository.reader.CategoryReader.readCategory;
import static ru.cft.focusstart.repository.reader.ManufacturerReader.readManufacturer;
import static ru.cft.focusstart.repository.reader.ProductReader.readProduct;

public class JdbcProductRepository implements ProductRepository {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    private static final String ADD_QUERY =
            "INSERT INTO task5.product (title, categoryId, productId, manufectureId, description) " +
                    "VALUES (?, ?, ?, ?, ?);";

    private static final String GET_QUERY =
            "SELECT * FROM task5.product AS p" +
                    " INNER JOIN task5.manufecture AS m ON m.id = p.manufectureId" +
                    " INNER JOIN task5.category AS c ON c.id = p.categoryId";

    private static final String GET_BY_MANUFACTURERS_ID_QUERY =
            GET_QUERY +
                    " WHERE p.manufectureId = ?;";

    private static final String GET_BY_CATEGORIES_ID_QUERY =
            GET_QUERY +
                    " WHERE p.categoryId = ?;";

    private static final String GET_BY_ID_QUERY =
            GET_QUERY +
                    " WHERE p.id = ?;";

    private static final String GET_BY_NAME_QUERY =
            GET_QUERY +
                    " WHERE LOWER(p.title) LIKE LOWER(?)" +
                    " AND LOWER(m.title) LIKE LOWER(?);";

    private static final String UPDATE_QUERY =
            "UPDATE task5.product AS p " +
                    " SET p.title = ?, p.categoryId = ?, p.productId = ?, p.manufectureId = ?, p.description = ?" +
                    " WHERE p.id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM task5.product WHERE id = ?;";

    private static final JdbcProductRepository INSTANCE = new JdbcProductRepository();

    private final DataSource dataSource;

    private JdbcProductRepository() {
        this.dataSource = DataSourceProvider.getDataSource();
    }

    public static JdbcProductRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Product product) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, product.getTitle());
            ps.setLong(2, product.getCategory().getId());
            ps.setString(3, product.getVendorCode());
            ps.setLong(4, product.getManufacturer().getId());
            ps.setString(5, product.getDescription());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            Long id = rs.next() ? rs.getLong(1) : null;
            if (id == null) {
                throw new SQLException("Unexpected error - could not obtain id");
            }
            product.setId(id);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_ID_QUERY)
        ) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            Collection<Product> products = readProductsList(rs);

            if (products.isEmpty()) {
                return Optional.empty();
            } else if (products.size() == 1) {
                return Optional.of(products.iterator().next());
            } else {
                throw new SQLException("Unexpected result set size");
            }

        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Product> get(String name, String manufacturerTitle) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_NAME_QUERY)
        ) {
            ps.setString(1, name == null ? "" : name);
            ps.setString(2, manufacturerTitle == null ? "" : manufacturerTitle);
            log.info("Name: {}", name);
            log.info("PreparedStatement: {}", ps.toString());
            ResultSet rs = ps.executeQuery();
            Collection<Product> products = readProductsList(rs);

            return new ArrayList<>(products);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Product> getByManufacturerId(Long id) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_MANUFACTURERS_ID_QUERY)
        ) {
            ps.setLong(1, id);
            log.info("Id: {}", id);
            log.info("PreparedStatement: {}", ps.toString());
            ResultSet rs = ps.executeQuery();

            Collection<Product> products = readProductsList(rs);

            return new ArrayList<>(products);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Product> getByCategoryId(Long id) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_CATEGORIES_ID_QUERY)
        ) {
            ps.setLong(1, id);
            log.info("Id: {}", id);
            log.info("PreparedStatement: {}", ps.toString());
            ResultSet rs = ps.executeQuery();

            Collection<Product> products = readProductsList(rs);

            return new ArrayList<>(products);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public void update(Product product) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)
        ) {
            ps.setString(1, product.getTitle());
            ps.setLong(2, product.getCategory().getId());
            ps.setString(3, product.getVendorCode());
            ps.setLong(4, product.getManufacturer().getId());
            ps.setString(5, product.getDescription());
            ps.setLong(6, product.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public void delete(Product product) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE_QUERY)
        ) {
            ps.setLong(1, product.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    private Collection<Product> readProductsList(ResultSet rs) throws SQLException {
        List<Product> result = new ArrayList<>();
        while (rs.next()) {
            Product product = readProduct(rs);
            log.info("Product: {}", product);

            Category category = readCategory(rs);
            product.setCategory(category);

            log.info("Category: {}", category);

            Manufacturer manufacturer = readManufacturer(rs);
            product.setManufacturer(manufacturer);

            log.info("Manufacturer: {}", manufacturer);

            log.info("Product: {}", product);

            result.add(product);
        }
        return result;
    }
}
