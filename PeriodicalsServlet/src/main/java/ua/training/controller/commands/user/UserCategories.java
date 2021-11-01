package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Category;
import ua.training.model.service.CategoriesService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserCategories implements Command {

    private final CategoriesService categoriesService;

    public UserCategories(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Category> categories = categoriesService.getAllCategories();
        request.setAttribute("categories" , categories);
        return "/WEB-INF/user/user_categories.jsp";
    }
}
