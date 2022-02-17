package ua.training.model.service;

import ua.training.model.dao.CategoriesDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Category;

import java.util.List;

/**
 * The Categories service.
 */
public class CategoriesService {
    /**
     * The DAO factory.
     */
    DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * Get list of all categories.
     *
     * @return the list
     */
    public List<Category> getAllCategories(){
        try (CategoriesDao dao = daoFactory.createCategoryDao()) {
            return dao.findAll();
        }
    }
}