package ua.training.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * The generic Object mapper interface.
 *
 * @param <T> the type parameter
 */
public interface ObjectMapper<T> {

    /**
     * Extract from result set t.
     *
     * @param rs the rs
     * @return the t
     * @throws SQLException the sql exception
     */
    T extractFromResultSet(ResultSet rs) throws SQLException;

    /**
     * Make unique t.
     *
     * @param cache the cache
     * @param user  the user
     * @return the t
     */
    T makeUnique(Map<Long, T> cache,
                 T user);
}
