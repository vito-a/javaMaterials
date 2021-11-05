package ua.training.model.dao.impl;

import ua.training.controller.commands.Login;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.RoleMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.service.RoleService;

import java.sql.*;
import java.util.*;

import static ua.training.model.constants.Constants.ROLE_USER;

/**
 * The JDBC User DAO.
 */
public class JDBCUserDao implements UserDao {
    private Connection connection;
    private final Logger logger = LogManager.getLogger(Login.class.getName());

    /**
     * Instantiates a new JDBC User DAO.
     *
     * @param connection the connection
     */
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
            logger.error("Cannot create user with params (name, password, firstName, userRole) : " +
                    "(" + entity.getUsername() + "," + entity.getPassword() + "," + entity.getFirstname() + "," + entity.getRoles() + ")", e);
        }

        return affectedRows;
    }

    /**
     * Add role.
     *
     * @param entity   the entity
     * @param roleName the role name
     */
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
            logger.error("Cannot add user role with params (name, role) : " +
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
        try (PreparedStatement ps = connection.prepareCall(query); ResultSet rs = ps.executeQuery(query);) {
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

    /**
     * Query used to return the users of role.
     * The method allows to get sorted list of users by
     * name or ID in one direction or opposite.
     *
     * @param roleId the role ID of users
     * @param offset value allow to retrieve just a portion of the rows
     * @param recordsOnPage the amount of data per request
     * @param sorting the way of the data sorting
     * @param sortingType the possible sorting type, e.g. name or price
     * @return list of users
     */
    public List<User> getAllUsers(long roleId, int offset, int recordsOnPage,
                                         Sorting sorting, SortingType sortingType) {
        ArrayList<User> users = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        String query = "SELECT * FROM users u" +
                " LEFT JOIN users_roles ur ON u.user_id = ur.user_id " +
                " LEFT JOIN roles r ON r.role_id = ur.role_id";
        queryBuilder.append(query);
        if (roleId > 0) {
            queryBuilder.append(" WHERE ur.role_id = ").append(roleId);
        }
        if (!Sorting.DEFAULT.equals(sorting)) {
            queryBuilder.append(" ORDER BY ").append("u.").append(sortingType.getValue()).append(" ").append(sorting.getType());
        }
        queryBuilder.append(" LIMIT ").append(offset).append(", ").append(recordsOnPage);

        try (PreparedStatement ps = connection.prepareCall(queryBuilder.toString()); ResultSet rs = ps.executeQuery();) {
            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();
            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                Role role = roleMapper.extractFromResultSet(rs);
                user.getRoles().add(role);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int update(User entity) {
       return 0;
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

    /**
     * Return the total count of users for a certain role.
     */
    public int getUsersCount(long roleId) {
        int usersCount = 0;
        final String query = "SELECT COUNT(*) AS count FROM users u" +
                " LEFT JOIN users_roles ur ON u.user_id = ur.user_id " +
                " LEFT JOIN roles r ON r.role_id = ur.role_id";
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(query);
        if (roleId > 0) {
            queryBuilder.append(" WHERE ur.role_id = ").append(roleId);
        }
        try (PreparedStatement ps = connection.prepareCall(queryBuilder.toString()); ResultSet rs = ps.executeQuery();) {
            if (rs.next()) {
                usersCount = rs.getInt("count");
            }
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return usersCount;
    }

    /**
     * Setting enabled status for a user.
     */
    @Override
    public int setEnabled(Long userId, Boolean enabled) {
        int affectedRows = 0;
        String balanceQuery = "UPDATE users SET enabled = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(balanceQuery)) {
            ps.setBoolean( 1, enabled);
            ps.setLong( 2, userId);
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Setting enabled status failed, no rows affected.");
            }
        } catch (SQLException e) {
            logger.error("Cannot set enabled status with params (enabled, userId) : " + "(" + enabled + "," + userId + ")", e);
        }
        return affectedRows;
    }
}
