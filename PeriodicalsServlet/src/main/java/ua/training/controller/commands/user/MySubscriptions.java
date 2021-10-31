package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Subscription;
import ua.training.model.entity.User;
import ua.training.model.service.SubscriptionsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MySubscriptions implements Command {

    private final SubscriptionsService subscriptionsService;

    public MySubscriptions(SubscriptionsService subscriptionsService) { this.subscriptionsService = subscriptionsService; }

    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");
        List<Subscription> subscriptions = subscriptionsService.getMySubscriptions(currentUser.getId());
        request.setAttribute("subscriptions", subscriptions);
        return "/WEB-INF/user/my_subscriptions.jsp";
    }
}
