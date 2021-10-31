package ua.training.controller.commands.admin;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Category;
import ua.training.model.entity.Subscription;
import ua.training.model.service.CategoriesService;
import ua.training.model.service.SubscriptionsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SubscriptionsList implements Command {

    private final SubscriptionsService subscriptionsService;

    public SubscriptionsList(SubscriptionsService subscriptionsService) { this.subscriptionsService = subscriptionsService; }

    @Override
    public String execute(HttpServletRequest request) {
        List<Subscription> subscriptions = subscriptionsService.getAllSubscriptions();
        request.setAttribute("subscriptions" , subscriptions);
        return "/WEB-INF/admin/subscriptions.jsp";
    }
}
