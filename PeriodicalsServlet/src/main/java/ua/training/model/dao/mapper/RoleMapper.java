package ua.training.model.dao.mapper;

import ua.training.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * The Role mapper.
 */
public class RoleMapper implements ObjectMapper<Role> {

    @Override
    public Role extractFromResultSet(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setRoleId(rs.getInt("role_id"));
        role.setName(rs.getString("name"));
        return role;
    }

    public Role makeUnique(Map<Long, Role> cache,
                              Role role) {
        cache.putIfAbsent(role.getRoleId(), role);
        return cache.get(role.getRoleId());
    }
}
