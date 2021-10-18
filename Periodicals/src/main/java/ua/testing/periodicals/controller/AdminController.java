package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.repository.PeriodicalsRepository;
import ua.testing.periodicals.repository.SubscriptionsRepository;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.service.PeriodicalsService;
import ua.testing.periodicals.service.SubscriptionsService;
import ua.testing.periodicals.service.UsersService;

import java.util.List;
import java.util.Optional;

/**
 * The Admin controller.
 */
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

    @Autowired
    private UsersService usersService;

    @Autowired
    private SubscriptionsService subscriptionsService;

    /**
     * List users.
     *
     * @param model the model
     * @return users list template name
     */
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users.html";
    }

    /**
     * List subscriptions.
     *
     * @param model the model
     * @return subscriptions list template name
     */
    @GetMapping("/subscriptions")
    public String listSubscriptions(Model model) {
        List<Subscription> listSubscriptions = subscriptionsService.listAll(null);
        model.addAttribute("listSubscriptions", listSubscriptions);

        return "subscriptions.html";
    }

    /**
     * Show new user form.
     *
     * @param model the model
     * @return user form template name
     */
    @RequestMapping("/user/new")
    public String showNewUserForm(Model model) {
        User User = new User();
        model.addAttribute("User", User);

        return "new_user.html";
    }

    /**
     * Save user.
     *
     * @param user the user
     * @return the redirection request endpoint
     */
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("User") User user) {
        usersService.save(user);

        return "redirect:/";
    }

    /**
     * User edit form.
     *
     * @param userId the user id
     * @return the model and view
     */
    @RequestMapping("/user/edit/{id}")
    public ModelAndView showEditUserForm(@PathVariable(name = "id") Long userId) {
        ModelAndView mav = new ModelAndView("user/edit_user.html");

        Optional<User> user = userRepo.getUserByUserId(userId);
        if (user.isPresent()) {
            mav.addObject("user", user);
        }

        return mav;
    }

    /**
     * Delete user.
     *
     * @param userId the user id
     * @return the redirection request endpoint
     */
    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long userId) {
        usersService.delete(userId);

        return "redirect:/";
    }
}