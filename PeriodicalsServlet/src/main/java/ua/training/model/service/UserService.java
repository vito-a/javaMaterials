package ua.training.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.dao.DaoFactory;

import java.util.List;
import java.util.Optional;

/**
 * The User service.
 */
public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    private final Logger logger = LogManager.getLogger(UserService.class.getName());

    public Optional<User> login(String name){
        Optional<User> result; //= Optional.empty();
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findByName(name);
        }
        return result;
    }

    public List<User> getAllUsers() {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public void createUser(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(user);
            logger.info("Attempted to create user with params (userName, userRole) ==> " +
                    "(" + user.getUsername() + "," + user.getRoles() + ")");
        }
    }

}
