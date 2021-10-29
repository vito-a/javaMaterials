package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Login;
import ua.training.model.dao.PeriodicalDao;
import ua.training.model.dao.mapper.PeriodicalMapper;
import ua.training.model.entity.Periodical;

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
    public void create(Periodical entity) {
    }

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
            logger.info("Periodicals: " + rs);
            PeriodicalMapper mapper = new PeriodicalMapper();
            while (rs.next()) {
                logger.info("Periodical ID: " + rs.getLong("periodical_id"));
                logger.info("Periodical name: " + rs.getString("name"));
                listPeriodicals.add(mapper.extractFromResultSet(rs));
            }
        } catch (Exception e) {
            logger.error("Cannot get periodicals list", e);
            throw new RuntimeException(e);
        }
        return listPeriodicals;
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
