package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Role;
import ua.testing.periodicals.model.entity.User;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public List<User> getRoleByName(String name);
}
