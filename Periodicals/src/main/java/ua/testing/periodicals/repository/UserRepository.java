package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.login LIKE %?1%"
            + " OR u.email LIKE %?1%"
            + " OR u.fullname LIKE %?1%"
            + " OR u.firstname LIKE %?1%"
            + " OR u.lastname LIKE %?1%")
    public List<User> search(String keyword);
}
