package ua.training.model.service;

import ua.training.model.dao.CategoriesDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Category;
import ua.training.model.entity.User;

import java.util.Arrays;
import java.util.List;
/**
 * The Categories service.
 */
public class CategoriesService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Category> getAllCategories(){
        try (CategoriesDao dao = daoFactory.createCategoryDao()) {
            return dao.findAll();
        }
    }
}