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
import java.util.ArrayList;
import java.util.List;

public class JDBCPeriodicalDao implements PeriodicalDao {
    private Connection connection;
    private final Logger logger = LogManager.getLogger(Login.class.getName());

    public JDBCPeriodicalDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Periodical entity) { return 0; }

    @Override
    public Periodical findById(int id) {
        return null;
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

    @Override
    public void update(Periodical entity) {

    }

    @Override
    public void delete(int id) {

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
