package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.repository.PeriodicalsRepository;
import ua.testing.periodicals.repository.SubscriptionsRepository;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.service.PeriodicalsService;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PeriodicalsRepository periodicalsRepo;

    @Autowired
    private SubscriptionsRepository subscriptionRepo;

    @Autowired
    private PeriodicalsService service;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users.html";
    }

    @GetMapping("/subscriptions")
    public String listSubscriptions(Model model) {
        List<Subscription> listSubscriptions = subscriptionRepo.findAll();
        model.addAttribute("listSubscriptions", listSubscriptions);

        return "subscriptions.html";
    }
}