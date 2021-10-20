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

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByUserId(Long userId);
    @Query("SELECT u FROM User u WHERE u.username LIKE %?1%"
            + " OR u.email LIKE %?1%"
            + " OR u.fullName LIKE %?1%"
            + " OR u.firstName LIKE %?1%"
            + " OR u.lastName LIKE %?1%")
    List<User> search(String keyword);
    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.email = ?2")
    @Modifying
    void updateFailedAttempts(int failedAttempt, String email);
    @Query("UPDATE User u SET u.balance = ?1 where u.email = ?2")
    @Modifying
    void updateBalance(Double balance, String email);
}
