package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.periodicals.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {
    /**
     * Gets user by username.
     *
     * @param username the username
     * @return the user object
     */
    Optional<User> getUserByUsername(String username);

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user object
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Gets user by user id.
     *
     * @param userId the user id
     * @return the user object
     */
    Optional<User> getUserByUserId(Long userId);

    /**
     * Search list.
     *
     * @param keyword the keyword
     * @return the users list
     */
    @Query("SELECT u FROM User u WHERE u.username LIKE %?1%"
            + " OR u.email LIKE %?1%"
            + " OR u.fullName LIKE %?1%"
            + " OR u.firstName LIKE %?1%"
            + " OR u.lastName LIKE %?1%")
    List<User> search(String keyword);

    /**
     * Update failed attempts.
     *
     * @param failedAttempt the failed attempts number
     * @param email         the user email
     */
    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.email = ?2")
    @Modifying
    void updateFailedAttempts(int failedAttempt, String email);

    /**
     * Update balance.
     *
     * @param balance the user balance
     * @param email   the user email
     */
    @Query("UPDATE User u SET u.balance = ?1 where u.email = ?2")
    @Modifying
    void updateBalance(Double balance, String email);
}
