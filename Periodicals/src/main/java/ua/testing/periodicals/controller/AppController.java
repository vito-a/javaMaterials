package ua.testing.periodicals.controller;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ua.testing.periodicals.model.entity.Category;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.CategoriesRepository;
import ua.testing.periodicals.repository.PeriodicalsRepository;
import ua.testing.periodicals.repository.SubscriptionsRepository;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.service.CategoriesService;
import ua.testing.periodicals.service.PeriodicalsService;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PeriodicalsRepository periodicalsRepo;

    @Autowired
    private CategoriesRepository categoriesRepo;

    @Autowired
    private SubscriptionsRepository subscriptionRepo;

    @Autowired
    private PeriodicalsService periodicalsService;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("")
    public String viewHomePage() {
        return "index.html";
    }

    private String getCurrentUserName() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping("/login")
    public String get(Model model) {
        model.addAttribute("title", "Sign In form");
        return "login";
    }

    @GetMapping("/periodicals")
    public String listPeriodicalsAll(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> user = userRepo.getUserByUsername(currentUser.getUsername());
        user.ifPresent(value -> model.addAttribute("currentUser", value));
        return listPeriodicalsSortingPager(model, 1, "periodicalId", "asc", currentUser);
    }

    @RequestMapping("/periodicals/{pageNum}")
    public String listPeriodicalsSortingPager(Model model,
                           @PathVariable(name = "pageNum") int pageNum,
                           @Param("sortField") String sortField,
                           @Param("sortDir") String sortDir, @AuthenticationPrincipal UserDetails currentUser) {

        Optional<User> user = userRepo.getUserByUsername(currentUser.getUsername());
        Page<Periodical> page = periodicalsService.listAll(pageNum, sortField, sortDir);
        List<Periodical> listPeriodicals = page.getContent();

        user.ifPresent(value -> model.addAttribute("currentUser", value));
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listPeriodicals", listPeriodicals);

        return "periodicals.html";
    }

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> listCategories = categoriesService.listAll(Optional.empty());
        model.addAttribute("listCategories", listCategories);

        return "categories.html";
    }

    @RequestMapping("/search/periodicals")
    public String searchPeriodicals(Model model, @Param("keyword") String keyword) {
        List<Periodical> listPeriodicals = periodicalsService.listAll(keyword);
        model.addAttribute("listPeriodicals", listPeriodicals);
        model.addAttribute("keyword", keyword);
        return "search/periodicals.html";
    }

    @GetMapping("/user/my-subscriptions")
    public String listMySubscriptions(Model model) {
        String username = getCurrentUserName();
        List<Subscription> listSubscriptions = subscriptionRepo.findMySubscriptions(username);
        model.addAttribute("listSubscriptions", listSubscriptions);

        return "user/my_subscriptions.html";
    }

    @GetMapping("/about")
    public String listAbout(Model model) {
        List<Periodical> listPeriodicals = periodicalsRepo.findAll();
        model.addAttribute("listPeriodicals", listPeriodicals);

        return "about.html";
    }

}