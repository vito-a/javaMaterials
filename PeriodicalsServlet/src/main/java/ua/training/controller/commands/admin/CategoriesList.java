package ua.training.controller.commands.admin;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Category;
import ua.training.model.service.CategoriesService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CategoriesList implements Command {

    private final CategoriesService categoriesService;

    public CategoriesList(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Category> categories = categoriesService.getAllCategories();
        request.setAttribute("categories" , categories);
        return "/WEB-INF/admin/categories.jsp";
    }
}
