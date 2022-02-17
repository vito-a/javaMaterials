package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The Category periodicals command.
 */
public class CategoryPeriodicals implements Command {

    private final PeriodicalsService periodicalsService;

    /**
     * Instantiates a new Category periodicals command.
     *
     * @param periodicalsService the periodicals service
     */
    public CategoryPeriodicals(PeriodicalsService periodicalsService) {
        this.periodicalsService = periodicalsService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String category = request.getParameter("category");
        List<Periodical> periodicals = periodicalsService.categoryPeriodicals(Long.parseLong(category));
        request.setAttribute("category_name", category);
        request.setAttribute("periodicals", periodicals);
        return "/WEB-INF/user/category_periodicals.jsp";
    }
}
