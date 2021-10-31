package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Login;
import ua.training.model.dao.SubscriptionDao;
import ua.training.model.dao.mapper.SubscriptionMapper;
import ua.training.model.entity.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSubscriptionDao implements SubscriptionDao {
    private Connection connection;
    private final Logger logger = LogManager.getLogger(Login.class.getName());

    public JDBCSubscriptionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Subscription entity) {
    }

    @Override
    public Subscription findById(int id) {
        return null;
    }

    @Override
    public List<Subscription> findAll () {
        List<Subscription> listSubscriptions = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM subscriptions")) {
            rs = ps.executeQuery();
            SubscriptionMapper mapper = new SubscriptionMapper();
            while (rs.next()) {
                listSubscriptions.add(mapper.extractFromResultSet(rs));
            }
        } catch (Exception e) {
            logger.error("Cannot get subscriptions list", e);
            throw new RuntimeException(e);
        }
        return listSubscriptions;
    }


    @Override
    public void update(Subscription entity) {

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
