package ru.cft.focusstart.repository.manufacturer;

import ru.cft.focusstart.entity.Manufacturer;
import ru.cft.focusstart.repository.DataAccessException;
import ru.cft.focusstart.repository.entity.JdbcEntityRepository;

import java.sql.*;
import java.util.*;

import static ru.cft.focusstart.repository.reader.ManufacturerReader.readManufacturer;

public class JdbcManufacturerRepository extends JdbcEntityRepository implements ManufacturerRepository {
    private static final String ADD_QUERY =
            "INSERT INTO task5.manufacturer (title)" +
                    "VALUES (?)";

    private static final String GET_QUERY =
            "SELECT * FROM task5.manufacturer AS m";

    private static final String GET_BY_NAME_QUERY =
            GET_QUERY +
                    " WHERE LOWER(m.title) LIKE LOWER(CONCAT( '%',?,'%'));";

    private static final String GET_BY_ID_QUERY =
            GET_QUERY +
                    " WHERE m.id = ?;";

    private static final String GET_BY_CATEGORIES_ID_QUERY =
            GET_QUERY +
                    " INNER JOIN task5.product AS p ON m.id = p.manufacturerId" +
                    " INNER JOIN task5.category AS c ON c.id = p.categoryId" +
                    " WHERE c.id = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE task5.manufacturer AS m" +
                    " SET m.title = ?" +
                    " WHERE m.id = ?;";

    private static final JdbcManufacturerRepository INSTANCE = new JdbcManufacturerRepository();

    private JdbcManufacturerRepository() {
        super();
    }

    public static ManufacturerRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Manufacturer manufacturer) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, manufacturer.getTitle());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            Long id = rs.next() ? rs.getLong(1) : null;
            if (id == null) {
                throw new SQLException("Unexpected error - could not obtain id");
            }
            manufacturer.setId(id);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Optional<Manufacturer> getById(Long id) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_ID_QUERY)
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Collection<Manufacturer> manufacturers = readManufacturersList(rs);

            if (manufacturers.isEmpty()) {
                return Optional.empty();
            } else if (manufacturers.size() == 1) {
                return Optional.of(manufacturers.iterator().next());
            } else {
                throw new SQLException("Unexpected result set size");
            }

        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Manufacturer> get(String... varargs) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_NAME_QUERY)
        ) {
            ps.setString(1, varargs.length == 0 ? "" : varargs[0]);

            ResultSet rs = ps.executeQuery();
            Collection<Manufacturer> manufacturers = readManufacturersList(rs);

            return new ArrayList<>(manufacturers);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)
        ) {
            ps.setString(1, manufacturer.getTitle());
            ps.setLong(2, manufacturer.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    private Collection<Manufacturer> readManufacturersList(ResultSet rs) throws SQLException {
        Map<Long, Manufacturer> result = new HashMap<>();

        while (rs.next()) {
            long manufacturerId = rs.getLong("id");

            Manufacturer manufacturer = result.get(manufacturerId);
            if (manufacturer == null) {
                manufacturer = readManufacturer(rs);
                result.put(manufacturerId, manufacturer);
            }
        }
        return result.values();
    }

    @Override
    public List<Manufacturer> getByCategoryId(Long id) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_BY_CATEGORIES_ID_QUERY)
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Collection<Manufacturer> manufacturers = readManufacturersList(rs);

            return new ArrayList<>(manufacturers);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
