package ua.training.model.dao.impl;

import ua.training.controller.commands.Login;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.RoleMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private Connection connection;
    private final Logger logger = LogManager.getLogger(Login.class.getName());

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        Map<Long, User> users = new HashMap<>();
        Map<Long, Role> roles = new HashMap<>();

        final String query = "SELECT * FROM users u" +
                " LEFT JOIN users_roles ur ON u.user_id = ur.user_id " +
                " LEFT JOIN roles r ON r.role_id = ur.role_id";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();

            while (rs.next()) {
                User user = userMapper
                        .extractFromResultSet(rs);
                Role role = roleMapper
                        .extractFromResultSet(rs);
                user = userMapper
                        .makeUnique(users, user);
                role = roleMapper
                        .makeUnique(roles, role);
                user.getRoles().add(role);
            }
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllUsers(Connection connection, int currentPage, int recordsPerPage) throws SQLException {
        List<User> listUsers = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users LIMIT ?, ?")) {
            int start = currentPage * recordsPerPage - recordsPerPage;
            int i = 1;
            ps.setInt(i++, start);
            ps.setInt(i++, recordsPerPage);
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            while (rs.next()) {
                listUsers.add(mapper.extractFromResultSet(rs));
            }
        } catch (Exception e) {
            logger.error("Cannot get users list", e);
            throw new RuntimeException(e);
        }
        return listUsers;

    }

    @Override
    public void update(User entity) {

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

    @Override
    public Optional<User> findByName(String name) {

        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM Users WHERE username = ?")){
            ps.setString( 1, name);
            ResultSet rs;
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()){
                result = Optional.of(mapper.extractFromResultSet(rs));
            }//TODO : ask question how avoid two Users with the same name
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }
}
