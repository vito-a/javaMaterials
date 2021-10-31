package ua.training.model.dao.impl;

import ua.training.model.dao.RoleDao;
import ua.training.model.dao.mapper.RoleMapper;
import ua.training.model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class JDBCRoleDao implements RoleDao {
    private Connection connection;

    public JDBCRoleDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Role entity) {

    }

    @Override
    public Role findById(int id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public void update(Role entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close()  {

    }

    public Optional<Role> findByName(String name) {
        Optional<Role> role = Optional.empty();
        final String query = "SELECT * FROM Roles r WHERE name = ?";
        try (PreparedStatement ps = connection.prepareCall(query)){
            ps.setString( 1, name);
            ResultSet rs = ps.executeQuery();
            RoleMapper roleMapper = new RoleMapper();
            if (rs.next()) {
                role = Optional.of(roleMapper.extractFromResultSet(rs));
            }
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return role;
    }
}
