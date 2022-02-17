package ua.training.model.dao;

import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The User DAO interface.
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Find user by name.
     *
     * @param name the name
     * @return the optional
     */
    Optional<User> findByName(String name);

    /**
     * Update user balance.
     *
     * @param balance the balance
     * @param userId  the user id
     * @return the int
     */
    int updateBalance(Double balance, Long userId);

    /**
     * Gets current user balance.
     *
     * @param userId the user id
     * @return the current balance
     */
    double getCurrentBalance(long userId);

    /**
     * Gets all users.
     *
     * @param roleId        the role id
     * @param offset        the offset
     * @param recordsOnPage the records on page
     * @param sorting       the sorting
     * @param sortingType   the sorting type
     * @return the all users
     */
    List<User> getAllUsers(long roleId, int offset, int recordsOnPage, Sorting sorting, SortingType sortingType);

    /**
     * Gets users count.
     *
     * @param activeRoleId the active role id
     * @return the users count
     */
    int getUsersCount(long activeRoleId);

    /**
     * Sets user enabled status.
     *
     * @param userId  the user id
     * @param enabled the enabled
     * @return the enabled
     */
    int setEnabled(Long userId, Boolean enabled);
}
