package ru.cft.focusstart.repository.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.api.logging.LoggingFilter;
import ru.cft.focusstart.entity.Category;

import javax.sql.DataSource;

import ru.cft.focusstart.entity.Manufacturer;
import ru.cft.focusstart.entity.Product;
import ru.cft.focusstart.repository.DataAccessException;
import ru.cft.focusstart.repository.DataSourceProvider;

import java.sql.*;
import java.util.*;

import static ru.cft.focusstart.repository.reader.CategoryReader.readCategory;
import static ru.cft.focusstart.repository.reader.ProductReader.readProduct;

public class JdbcCategoryRepository implements CategoryRepository {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    private static final String ADD_QUERY =
            "INSERT INTO task5.category (name) " +
                    "values (?);";

    private static final String GET_QUERY =
            "SELECT DISTINCT * FROM task5.category AS c";

    private static final String GET_BY_MANUFACTURERS_ID_QUERY =
            GET_QUERY +
                    " INNER JOIN task5.product AS p ON c.id = p.categoryId" +
                    " INNER JOIN task5.manufecture AS m ON m.id = p.manufectureId" +
                    " WHERE m.id = ?;";

    private static final String GET_BY_NAME_QUERY =
            GET_QUERY +
                    " WHERE LOWER(c.name) LIKE LOWER(?)";

    private static final String GET_BY_ID_QUERY =
            GET_QUERY +
                    " WHERE c.id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE task5.category AS c" +
                    " SET c.name = ?" +
                    " WHERE c.id = ?;";


    private static final JdbcCategoryRepository INSTANCE = new JdbcCategoryRepository();

    private final DataSource dataSource;

    private JdbcCategoryRepository() {
        this.dataSource = DataSourceProvider.getDataSource();
    }

    public static CategoryRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Category category) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, category.getName());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            Long id = rs.next() ? rs.getLong(1) : null;
            if (id == null) {
                throw new SQLException("Unexpected error - could not obtain id");
            }
            category.setId(id);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Optional<Category> getById(Long id) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_ID_QUERY)
        ) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            Collection<Category> categories = readCategoriesList(rs);

            if (categories.isEmpty()) {
                return Optional.empty();
            } else if (categories.size() == 1) {
                return Optional.of(categories.iterator().next());
            } else {
                throw new SQLException("Unexpected result set size");
            }

        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Category> get(String name) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_NAME_QUERY)
        ) {
            ps.setString(1, name == null ? "" : name);
            log.info("Name: {}", name);
            log.info("PreparedStatement: {}", ps.toString());
            ResultSet rs = ps.executeQuery();
            log.info("ResultSet: {}", rs.toString());
            Collection<Category> categories = readCategoriesList(rs);

            return new ArrayList<>(categories);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Category> getByManufacturerId(Long id) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_MANUFACTURERS_ID_QUERY)
        ) {
            ps.setLong(1, id);
            log.info("Id: {}", id);
            log.info("PreparedStatement: {}", ps.toString());
            ResultSet rs = ps.executeQuery();
            log.info("ResultSet: {}", rs.toString());
            Collection<Category> categories = readCategoriesList(rs);

            return new ArrayList<>(categories);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public void update(Category category) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)
        ) {
            ps.setString(1, category.getName());
            ps.setLong(2, category.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    private Collection<Category> readCategoriesList(ResultSet rs) throws SQLException {
        Map<Long, Category> result = new HashMap<>();
        while (rs.next()) {
            long categoryId = rs.getLong("id");

            Category category = result.get(categoryId);
            if (category == null) {
                category = readCategory(rs);
                result.put(categoryId, category);
            }
            log.info("Category: {}", category);
        }
        return result.values();
    }
}
