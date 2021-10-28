package ua.training.model.dao.mapper;

import ua.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User User = new User();
        User.setId(rs.getInt("idUser"));
        User.setName(rs.getString("User.name"));
        User.setEnabled(rs.getBoolean("enabled"));
        User.setRoleId(rs.getInt("RoleId"));
        return User;
    }

    public User makeUnique(Map<Long, User> cache,
                              User User) {
        cache.putIfAbsent(User.getId(), User);
        return cache.get(User.getId());
    }
}
