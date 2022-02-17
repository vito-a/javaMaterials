package ua.training.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.User;
import ua.training.model.dao.DaoFactory;

import java.util.List;
import java.util.Optional;

/**
 * The User service.
 */
public class UserService {
    /**
     * The Dao factory.
     */
    DaoFactory daoFactory = DaoFactory.getInstance();

    private final Logger logger = LogManager.getLogger(UserService.class.getName());

    /**
     * Login.
     *
     * @param name the name
     * @return the optional
     */
    public Optional<User> login(String name){
        Optional<User> result; //= Optional.empty();
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findByName(name);
        }
        return result;
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    public List<User> getAllUsers() {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    /**
     * Gets all users with pagination and sorting.
     *
     * @param roleId        the role id
     * @param offset        the offset
     * @param recordsOnPage the records on page
     * @param sorting       the sorting
     * @param sortingType   the sorting type
     * @return the all users
     */
    public List<User> getAllUsers(long roleId, int offset, int recordsOnPage,
                                  Sorting sorting, SortingType sortingType) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.getAllUsers(roleId, offset, recordsOnPage, sorting, sortingType);
        }
    }

    /**
     * Create user.
     *
     * @param user the user
     * @return the int
     */
    public int createUser(User user) {
        int result = 0;
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.create(user);
            logger.info("Attempted to create user with params (userName, userRole) : " +
                    "(" + user.getUsername() + "," + user.getRoles() + ")");
        }

        return result;
    }

    /**
     * Update user balance.
     *
     * @param balance the user balance
     * @param userId  the user ID
     * @return the int
     */
    public int updateBalance(Double balance, Long userId) {
        int result = 0;
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.updateBalance(balance, userId);
            logger.info("Attempted to create user with params (balance, userId) : " + "(" + balance + "," + userId + ")");
        }

        return result;
    }

    /**
     * Get current user balance.
     *
     * @param userId the user id
     * @return the current balance
     */
    public double getCurrentBalance(long userId) {
        double balance = 0;
        try (UserDao userDao = daoFactory.createUserDao()) {
            balance = userDao.getCurrentBalance(userId);
            logger.info("Attempted to get user balance with params (userId) : " + "(" + userId + ")");
        }

        return balance;
    }

    /**
     * Gets users count.
     *
     * @param activeRoleId the active role id
     * @return the users count
     */
    public int getUsersCount(long activeRoleId) {
        int usersCount = 0;
        try (UserDao userDao = daoFactory.createUserDao()) {
            usersCount = userDao.getUsersCount(activeRoleId);
        }

        return usersCount;
    }

    /**
     * Sets enabled status.
     *
     * @param userId  the user id
     * @param enabled the enabled
     * @return the enabled
     */
    public int setEnabled(Long userId, Boolean enabled) {
        int result = 0;
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.setEnabled(userId, enabled);
            logger.info("Attempted to set enabled status for user with params (userId, enabled) : " + "(" + userId + "," + enabled + ")");
        }

        return result;
    }
}
