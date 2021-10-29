package ua.training.model.service;

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

    public Optional<User> login(String name){
        Optional<User> result; //= Optional.empty();
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findByName(name);
        }
        return result;
    }

    public List<User> getAllUsers(){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }
}
