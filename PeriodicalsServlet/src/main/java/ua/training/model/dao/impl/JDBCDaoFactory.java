package ua.training.model.dao.impl;

import ua.training.model.dao.*;

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
    public PeriodicalDao createStudentDao() {
        return new JDBCPeriodicalDao(getConnection());
    }
    @Override
    public CategoriesDao createCategoryDao() { return new JDBCCategoryDao(getConnection()); }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
