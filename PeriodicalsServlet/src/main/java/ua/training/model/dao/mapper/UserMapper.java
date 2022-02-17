package ua.training.model.dao.mapper;

import ua.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * The User mapper.
 */
public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setPassword(rs.getString("password"));
        user.setUsername(rs.getString("username"));
        user.setFirstname(rs.getString("firstname"));
        user.setEmail(rs.getString("email"));
        user.setLastname(rs.getString("lastname"));
        user.setEnabled(rs.getBoolean("enabled"));
        return user;
    }

    public User makeUnique(Map<Long, User> cache, User User) {
        cache.putIfAbsent(User.getId(), User);
        return cache.get(User.getId());
    }
}
