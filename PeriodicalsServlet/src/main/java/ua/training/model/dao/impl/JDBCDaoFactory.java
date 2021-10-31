package ua.training.model.dao.impl;

import ua.training.model.dao.*;
import ua.training.model.entity.Subscription;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }
    @Override
    public PeriodicalDao createPeriodicalDao() {
        return new JDBCPeriodicalDao(getConnection());
    }
    @Override
    public CategoriesDao createCategoryDao() { return new JDBCCategoryDao(getConnection()); }
    @Override
    public SubscriptionDao createSubscriptionDao() { return new JDBCSubscriptionDao(getConnection()); }
    @Override
    public RoleDao createRoleDao() { return new JDBCRoleDao(getConnection()); }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
