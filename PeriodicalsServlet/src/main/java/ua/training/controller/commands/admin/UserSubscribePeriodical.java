package ua.training.controller.commands.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.commands.Registration;
import ua.training.model.service.PeriodicalsService;

import javax.servlet.http.HttpServletRequest;

public class UserSubscribePeriodical implements Command {

    private final PeriodicalsService periodicalService;
    private final Logger logger = LogManager.getLogger(Registration.class.getName());

    public UserSubscribePeriodical(PeriodicalsService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "redirect:/app/user/periodicals";
    }
}
