package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Login;
import ua.training.model.dao.PeriodicalDao;
import ua.training.model.dao.mapper.PeriodicalMapper;
import ua.training.model.dao.mapper.RoleMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ua.training.model.constants.Constants.ROLE_USER;

/**
 * The JDBC Periodical DAO.
 */
public class JDBCPeriodicalDao implements PeriodicalDao {
    private Connection connection;
    private final Logger logger = LogManager.getLogger(Login.class.getName());

    /**
     * Instantiates a new JDBC Periodical DAO.
     *
     * @param connection the connection
     */
    public JDBCPeriodicalDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Periodical entity) {
        int affectedRows = 0;
        String[] generatedColumns = {"periodical_id"};
        String userQuery = "INSERT INTO periodicals (name, description, cat_id, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(userQuery, generatedColumns)) {
            ps.setString( 1, entity.getName());
            ps.setString(2, entity.getDescription());
            ps.setLong(3, entity.getCatId());
            ps.setDouble(4, entity.getPrice());
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating periodical failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating periodical failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Cannot create periodical with params (name, description, catId, price) : " +
                    "(" + entity.getName() + "," + entity.getDescription() + "," + entity.getCatId() + "," + entity.getPrice() + ")", e);
        }

        return affectedRows;
    }

    @Override
    public Periodical findById(int id) {
        Periodical periodical = new Periodical();
        String query = "SELECT * FROM periodicals WHERE periodical_id = ?";
        try (PreparedStatement ps = connection.prepareCall(query);) {
            ps.setInt( 1, id);
            ResultSet rs = ps.executeQuery();
            PeriodicalMapper mapper = new PeriodicalMapper();
            while (rs.next()) {
                periodical = mapper.extractFromResultSet(rs);
            }
            return periodical;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Periodical> findAll () {
        List<Periodical> listPeriodicals = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM periodicals")) {
            rs = ps.executeQuery();
            PeriodicalMapper mapper = new PeriodicalMapper();
            while (rs.next()) {
                listPeriodicals.add(mapper.extractFromResultSet(rs));
            }
        } catch (Exception e) {
            logger.error("Cannot get periodicals list", e);
            throw new RuntimeException(e);
        }
        return listPeriodicals;
    }

    /**
     * Query used to return the periodicals.
     * The method allows to get sorted list of periodicals by
     * name or ID in one direction or opposite.
     *
     * @param offset value allow to retrieve just a portion of the rows
     * @param recordsOnPage the amount of data per request
     * @param sorting the way of the data sorting
     * @param sortingType the possible sorting type, e.g. name or price
     * @return list of periodicals
     */
    public List<Periodical> getAllPeriodicals(int offset, int recordsOnPage,
                                  Sorting sorting, SortingType sortingType) {
        List<Periodical> listPeriodicals = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        String query = "SELECT * FROM periodicals";
        queryBuilder.append(query);
        if (!Sorting.DEFAULT.equals(sorting)) {
            queryBuilder.append(" ORDER BY ").append(sortingType.getValue()).append(" ").append(sorting.getType());
        }
        queryBuilder.append(" LIMIT ").append(offset).append(", ").append(recordsOnPage);
        try (PreparedStatement ps = connection.prepareCall(queryBuilder.toString()); ResultSet rs = ps.executeQuery();) {
            PeriodicalMapper mapper = new PeriodicalMapper();
            while (rs.next()) {
                listPeriodicals.add(mapper.extractFromResultSet(rs));
            }
            return listPeriodicals;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Return the total count of periodicals for a certain category.
     */
    @Override
    public int getPeriodicalsCount(long catId) {
        int periodicalsCount = 0;
        final String query = "SELECT COUNT(*) AS count FROM periodicals";
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(query);
        if (catId > 0) {
            queryBuilder.append(" WHERE catId = ").append(catId);
        }
        try (PreparedStatement ps = connection.prepareCall(queryBuilder.toString()); ResultSet rs = ps.executeQuery();) {
            if (rs.next()) {
                periodicalsCount = rs.getInt("count");
            }
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return periodicalsCount;
    }

    /**
     * Search list.
     *
     * @param keyword the keyword
     * @return the periodicals list
     */
    @Override
    public List<Periodical> search(String keyword) {
        List<Periodical> listPeriodicals = new ArrayList<>();
        String query = "SELECT * FROM periodicals p WHERE p.name LIKE ?"
                + " OR p.description LIKE ?"
                + " OR p.cat_id LIKE ?"
                + " OR CONCAT(p.price, '') LIKE ?";
        try (PreparedStatement ps = connection.prepareCall(query);) {
            String keywordWildcard = "%" + keyword + "%";
            ps.setString( 1, keywordWildcard);
            ps.setString( 2, keywordWildcard);
            ps.setString( 3, keywordWildcard);
            ps.setString( 4, keywordWildcard);
            ResultSet rs = ps.executeQuery();
            PeriodicalMapper mapper = new PeriodicalMapper();
            while (rs.next()) {
                listPeriodicals.add(mapper.extractFromResultSet(rs));
            }
            return listPeriodicals;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Search list.
     *
     * @param catId the category ID
     * @return the periodicals list
     */
    @Override
    public List<Periodical> categoryPeriodicals(long catId) {
        List<Periodical> listPeriodicals = new ArrayList<>();
        String query = "SELECT * FROM periodicals WHERE cat_id = ?";
        try (PreparedStatement ps = connection.prepareCall(query);) {
            ps.setLong( 1, catId);
            ResultSet rs = ps.executeQuery();
            PeriodicalMapper mapper = new PeriodicalMapper();
            while (rs.next()) {
                listPeriodicals.add(mapper.extractFromResultSet(rs));
            }
            return listPeriodicals;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int update(Periodical entity) {
        int affectedRows = 0;
        try (PreparedStatement ps = connection.prepareStatement("UPDATE periodicals " +
                " SET name = ?, description = ?, cat_id = ?, price = ? WHERE periodical_id = ?")) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDescription());
            ps.setLong(3, entity.getCatId());
            ps.setDouble(4, entity.getPrice());
            ps.setLong(5, entity.getId());
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating periodical failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating periodical failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Cannot create periodical with params (name, description, catId, price) : " +
                    "(" + entity.getName() + "," + entity.getDescription() + "," + entity.getCatId() + "," + entity.getPrice() + ")", e);
        }

        return affectedRows;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM periodicals WHERE periodical_id = ?";
        try (PreparedStatement ps = connection.prepareCall(query)) {
            ps.setInt( 1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int subscribe(int periodicalId, long userId) {
        long subId = 0L;
        int affectedRows = 0;
        String[] generatedColumns = {"sub_id"};
        String userQuery = "INSERT INTO subscriptions (user_id, periodical_id, startdate, enddate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(userQuery, generatedColumns)) {
            LocalDate startDate = LocalDate.now();
            logger.info("subscription startDate = " + startDate);
            LocalDate endDate = LocalDate.now().plusYears(1);
            logger.info("subscription endDate = " + endDate);
            ps.setLong( 1, userId);
            ps.setInt(2, periodicalId);
            ps.setDate( 3, Date.valueOf(startDate));
            ps.setDate(4, Date.valueOf(endDate));
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating subscription failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subId = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating subscription failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Cannot create subscription with params (sub_id, user_id, periodical_id) : " +
                    "(" + subId + "," + userId + "," + periodicalId + ")", e);
        }

        return affectedRows;
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
