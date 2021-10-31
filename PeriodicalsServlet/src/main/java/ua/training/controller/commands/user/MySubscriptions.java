package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Subscription;
import ua.training.model.entity.User;
import ua.training.model.service.SubscriptionsService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MySubscriptions implements Command {

    private final SubscriptionsService subscriptionsService;
    private final UserService userService;

    public MySubscriptions(SubscriptionsService subscriptionsService, UserService userService) { this.subscriptionsService = subscriptionsService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");
        List<Subscription> subscriptions = subscriptionsService.getMySubscriptions(currentUser.getId());
        double balance = userService.getCurrentBalance(currentUser.getId());
        request.setAttribute("subscriptions", subscriptions);
        request.setAttribute("balance", balance);
        return "/WEB-INF/user/my_subscriptions.jsp";
    }
}
