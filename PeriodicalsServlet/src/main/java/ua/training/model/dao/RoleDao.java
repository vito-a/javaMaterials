package ua.training.model.dao;

import ua.training.model.entity.Role;

import java.util.Optional;

public interface RoleDao extends GenericDao<Role> {
    Optional<Role> findByName(String name);
}
