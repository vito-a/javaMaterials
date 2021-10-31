package ua.training.model.dao;

import ua.training.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> findByName(String name);

    int updateBalance(Double balance, Long userId);

    double getCurrentBalance(long userId);
}
