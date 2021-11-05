package ua.training.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

/**
 * The Connection pool holder.
 *
 * Manages the JDBC connections pool.
 *
 */
public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    /**
     * Get data source data source.
     *
     * @return the data source
     */
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/periodicalsdb?useSSL=false");
                    ds.setUsername("root");
                    ds.setPassword("dhtsss");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }


}
