package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserPeriodicalsList implements Command {

    private final PeriodicalsService periodicalsService;

    public UserPeriodicalsList(PeriodicalsService periodicalsService) {
        this.periodicalsService = periodicalsService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Periodical> periodicals = periodicalsService.getAllPeriodicals();
        request.setAttribute("periodicals" , periodicals);
        return "/WEB-INF/user/user_periodicals.jsp";
    }
}
