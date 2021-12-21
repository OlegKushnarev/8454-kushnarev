package ru.cft.focusstart.repository.category;

import org.springframework.stereotype.Repository;
import ru.cft.focusstart.entity.Category;
import ru.cft.focusstart.repository.DataAccessException;
import ru.cft.focusstart.repository.entity.JdbcEntityRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

import static ru.cft.focusstart.repository.reader.CategoryReader.readCategory;

@Repository
public class JdbcCategoryRepository extends JdbcEntityRepository implements CategoryRepository {
    private static final String ADD_QUERY =
            "INSERT INTO task5.category (title) " +
                    "values (?);";

    private static final String GET_QUERY =
            "SELECT DISTINCT * FROM task5.category AS c";

    private static final String GET_BY_MANUFACTURERS_ID_QUERY =
            GET_QUERY +
                    " INNER JOIN task5.product AS p ON c.id = p.categoryId" +
                    " INNER JOIN task5.manufacturer AS m ON m.id = p.manufacturerId" +
                    " WHERE m.id = ?;";

    private static final String GET_BY_NAME_QUERY =
            GET_QUERY +
                    " WHERE LOWER(c.title) LIKE LOWER(CONCAT( '%',?,'%'))";

    private static final String GET_BY_ID_QUERY =
            GET_QUERY +
                    " WHERE c.id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE task5.category AS c" +
                    " SET c.title = ?" +
                    " WHERE c.id = ?;";

    public JdbcCategoryRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void add(Category category) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, category.getTitle());

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
    public List<Category> get(String categoryTitle) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_NAME_QUERY)
        ) {
            ps.setString(1, categoryTitle == null ? "" : categoryTitle);

            ResultSet rs = ps.executeQuery();
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

            ResultSet rs = ps.executeQuery();
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
            ps.setString(1, category.getTitle());
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
        }
        return result.values();
    }
}
