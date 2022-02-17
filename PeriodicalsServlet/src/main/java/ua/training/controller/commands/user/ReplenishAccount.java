package ua.training.controller.commands.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.commands.Registration;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Replenish account command.
 */
public class ReplenishAccount implements Command {

    private final UserService userService;

    private final Logger logger = LogManager.getLogger(Registration.class.getName());

    /**
     * Instantiates a new Replenish account command.
     *
     * @param userService the user service
     */
    public ReplenishAccount(UserService userService) { this.userService = userService; }

    @Override
    public String execute(HttpServletRequest request) {
        double balance = 0.0;
        String userId = request.getParameter("userId");
        String balanceParam = request.getParameter("balance");
        User currentUser = (User) request.getSession().getAttribute("user");
        logger.info("ReplenishAccount triggered");

        if( balanceParam == null || balanceParam.equals("") || userId == null || userId.equals("")  ){
            logger.info("ReplenishAccount form opened");
            request.setAttribute("userId", currentUser.getId());
            return "/WEB-INF/user/replenish_account.jsp";
        }

        try {
            balance = Double.parseDouble(balanceParam);
            logger.info("Balance : " + balanceParam);
        } catch (Exception e) {
            logger.info("Incorrect value for balance");
        }

        if (balance > 0.0) {
            double currentBalance = userService.getCurrentBalance(currentUser.getId());
            userService.updateBalance(currentBalance + balance, currentUser.getId());
            currentUser.setBalance(currentBalance);
        }
        logger.info("Incorrect balance. Please enter correct number.");

        return "redirect:/app/user/my-subscriptions";
    }
}
