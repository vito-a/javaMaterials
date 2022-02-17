package ua.training.controller.commands.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.commands.Registration;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalsService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The Admin User subscribe periodical command.
 */
public class UserSubscribePeriodical implements Command {

    private final UserService userService;
    private final PeriodicalsService periodicalService;
    private final Logger logger = LogManager.getLogger(Registration.class.getName());

    /**
     * Instantiates a new User subscribe periodical command.
     *
     * @param userService       the user service
     * @param periodicalService the periodical service
     */
    public UserSubscribePeriodical(UserService userService, PeriodicalsService periodicalService) {
        this.userService = userService;
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String periodicalId = request.getParameter("periodical_id");

        if (periodicalId != null && !periodicalId.equals("")) {
            HttpSession currentSession = request.getSession(false);
            User currentUser = (User) currentSession.getAttribute("user");
            logger.info("Periodical subscription (periodical_id, user_id) : " + "(" + periodicalId + ", " + currentUser.getId() + ")");
            Periodical periodical = PeriodicalsService.findById(Integer.parseInt(periodicalId));
            Double price = periodical.getPrice();
            PeriodicalsService.subscribe(Integer.parseInt(periodicalId), currentUser.getId());
            double currentBalance = userService.getCurrentBalance(currentUser.getId());
            userService.updateBalance(currentBalance - price, currentUser.getId());
            currentUser.setBalance(currentBalance);
            return "redirect:/app/user/my-subscriptions";
        }

        return "/WEB-INF/error.jsp";
    }
}
