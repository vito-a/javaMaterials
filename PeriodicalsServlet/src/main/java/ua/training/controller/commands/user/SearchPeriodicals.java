package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchPeriodicals implements Command {

    private final PeriodicalsService periodicalsService;

    public SearchPeriodicals(PeriodicalsService periodicalsService) {
        this.periodicalsService = periodicalsService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        List<Periodical> periodicals = periodicalsService.searchPeriodicals(keyword);
        request.setAttribute("periodicals", periodicals);
        return "/WEB-INF/user/search_periodicals.jsp";
    }
}
