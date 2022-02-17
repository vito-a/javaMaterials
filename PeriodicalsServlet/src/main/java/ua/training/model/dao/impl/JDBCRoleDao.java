package ua.training.model.dao.impl;

import ua.training.model.dao.RoleDao;
import ua.training.model.dao.mapper.RoleMapper;
import ua.training.model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The JDBC Role DAO.
 */
public class JDBCRoleDao implements RoleDao {
    private Connection connection;

    /**
     * Instantiates a new JDBC Role DAO.
     *
     * @param connection the connection
     */
    public JDBCRoleDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int create(Role entity) { return 0; }

    @Override
    public Role findById(int id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        ArrayList<Role> roles = new ArrayList<>();
        final String query = "SELECT * FROM roles";
        try (PreparedStatement ps = connection.prepareCall(query); ResultSet rs = ps.executeQuery(query);) {
            RoleMapper roleMapper = new RoleMapper();
            while (rs.next()) {
                Role role = roleMapper.extractFromResultSet(rs);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int update(Role entity) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close()  {

    }

    public Optional<Role> findByName(String name) {
        Optional<Role> role = Optional.empty();
        final String query = "SELECT * FROM roles WHERE name = ?";
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
