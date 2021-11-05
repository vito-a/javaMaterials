package ua.training.model.dao;

import java.util.List;

/**
 * The Generic DAO interface.
 *
 * @param <T> the type parameter
 */
public interface GenericDao<T> extends AutoCloseable {
    /**
     * Create entity.
     *
     * @param entity the entity
     * @return the int
     */
    int create (T entity);

    /**
     * Find entity by id.
     *
     * @param id the id
     * @return the t
     */
    T findById(int id);

    /**
     * Find all entities.
     *
     * @return the list
     */
    List<T> findAll();

    /**
     * Update entity.
     *
     * @param entity the entity
     * @return the int
     */
    int update(T entity);

    /**
     * Delete entity.
     *
     * @param id the id
     */
    void delete(int id);
    void close();
}
