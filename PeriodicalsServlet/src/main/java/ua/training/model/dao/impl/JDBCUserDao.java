package ua.training.model.dao.impl;

import ua.training.controller.commands.Login;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.RoleMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.service.RoleService;

import java.sql.*;
import java.util.*;

import static ua.training.model.constants.Constants.ROLE_USER;

public class JDBCUserDao implements UserDao {
    private Connection connection;
    private final Logger logger = LogManager.getLogger(Login.class.getName());

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(User entity) {
        int affectedRows = 0;
        String[] generatedColumns = {"user_id"};
        String userQuery = "INSERT INTO users (username, password, firstname, lastname, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(userQuery, generatedColumns)) {
            ps.setString( 1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getFirstname());
            ps.setString(4, entity.getLastname());
            ps.setString(5, entity.getEmail());
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                    addRole(entity, ROLE_USER);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Cannot create user with params (name, password, firstName, userRole) ==> " +
                    "(" + entity.getUsername() + "," + entity.getPassword() + "," + entity.getFirstname() + "," + entity.getRoles() + ")", e);
        }

        return affectedRows;
    }

    public void addRole(User entity, String roleName) {
        String roleQuery = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        RoleService roleService = new RoleService();
        Optional<Role> role = roleService.getByName(roleName);
        try (PreparedStatement ps = connection.prepareStatement(roleQuery)) {
            ps.setLong( 1, entity.getId());
            ps.setLong( 2, role.get().getRoleId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding role failed, no rows affected.");
            }
        } catch (SQLException e) {
            logger.error("Cannot add user role with params (name, role) ==> " +
                    "(" + entity.getUsername() + "," + role.get().getName() + ")", e);
        }
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
        try (PreparedStatement ps = connection.prepareCall(query)) {
            ResultSet rs = ps.executeQuery(query);
            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();
            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                Role role = roleMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
                role = roleMapper.makeUnique(roles, role);
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

        Optional<User> user = Optional.empty();
        Optional<Role> role;

        final String query = "SELECT * FROM users u" +
                " LEFT JOIN users_roles ur ON u.user_id = ur.user_id " +
                " LEFT JOIN roles r ON r.role_id = ur.role_id WHERE username = ?";
        try (PreparedStatement ps = connection.prepareCall(query)){
            ps.setString( 1, name);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();
            if (rs.next()) {
                user = Optional.of(userMapper.extractFromResultSet(rs));
                role = Optional.of(roleMapper.extractFromResultSet(rs));
                user.get().getRoles().add(role.get());
            }
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return user;
    }

    public int updateBalance(Double balance, Long userId) {
        int affectedRows = 0;
        String balanceQuery = "UPDATE users SET balance = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(balanceQuery)) {
            ps.setDouble( 1, balance);
            ps.setLong( 2, userId);
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding role failed, no rows affected.");
            }
        } catch (SQLException e) {
            logger.error("Cannot update balance with params (balance, userId) : " + "(" + balance + "," + userId + ")", e);
        }
        return affectedRows;
    }

    @Override
    public double getCurrentBalance(long userId) {
        double balance = 0.0;
        String balanceQuery = "SELECT balance FROM users WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(balanceQuery)) {
            ps.setLong( 1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
        } catch (SQLException e) {
            logger.error("Cannot get balance with params (userId) : " + "(" + userId + ")", e);
        }
        return balance;
    }
}
