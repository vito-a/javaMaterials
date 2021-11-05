package ua.training.model.dao;

import ua.training.model.entity.Role;

import java.util.Optional;

/**
 * The Role DAO interface.
 */
public interface RoleDao extends GenericDao<Role> {
    /**
     * Find role by name.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Role> findByName(String name);
}
