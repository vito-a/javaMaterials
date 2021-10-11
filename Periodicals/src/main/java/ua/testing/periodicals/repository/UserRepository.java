package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {
    User getUserByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.username LIKE %?1%"
            + " OR u.email LIKE %?1%"
            + " OR u.fullName LIKE %?1%"
            + " OR u.firstName LIKE %?1%"
            + " OR u.lastName LIKE %?1%")
    List<User> search(String keyword);
}
