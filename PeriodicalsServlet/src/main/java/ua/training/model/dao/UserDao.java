package ua.training.model.dao;

import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> findByName(String name);

    int updateBalance(Double balance, Long userId);

    double getCurrentBalance(long userId);

    List<User> getAllUsers(long roleId, int offset, int recordsOnPage, Sorting sorting, SortingType sortingType);

    int getUsersCount(long activeRoleId);

    int setEnabled(Long userId, Boolean enabled);
}
