package ua.training.model.dao;

import ua.training.model.entity.Category;

import java.util.Optional;

/**
 * The Categories DAO interface.
 */
public interface CategoriesDao extends GenericDao<Category> {

    /**
     * Find category by name.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Category> findByName(String name);
}
