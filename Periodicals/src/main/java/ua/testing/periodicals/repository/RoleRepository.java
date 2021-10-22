package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Role;
import ua.testing.periodicals.model.entity.User;

import java.util.List;

/**
 * The Role repository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Find by name role.
     *
     * @param name the user name
     * @return the role object
     */
    Role findByName(String name);

    /**
     * Gets role by name.
     *
     * @param name the user name
     * @return the roles list
     */
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    List<User> getRoleByName(String name);
}
