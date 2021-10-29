package ua.training.controller.commands.admin;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Category;
import ua.training.model.entity.Periodical;
import ua.training.model.service.CategoriesService;
import ua.training.model.service.PeriodicalsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PeriodicalsList implements Command {

    private final PeriodicalsService periodicalsService;

    public PeriodicalsList(PeriodicalsService periodicalsService) {
        this.periodicalsService = periodicalsService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Periodical> periodicals = periodicalsService.getAllPeriodicals();
        request.setAttribute("periodicals" , periodicals);
        return "/WEB-INF/admin/periodicals.jsp";
    }
}
