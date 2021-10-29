package ua.training.model.dao;

import ua.training.model.entity.Category;

import java.util.Optional;

public interface CategoriesDao extends GenericDao<Category> {

    Optional<Category> findByName(String name);
}
