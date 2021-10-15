package ua.testing.periodicals.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ua.testing.periodicals.model.dao.DBException;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.model.entity.Role;
import ua.testing.periodicals.repository.PeriodicalsRepository;
import ua.testing.periodicals.repository.RoleRepository;
import ua.testing.periodicals.repository.SubscriptionsRepository;
import ua.testing.periodicals.repository.UserRepository;

import static ua.testing.periodicals.model.constants.Constants.ROLE_USER;
import static ua.testing.periodicals.model.constants.Constants.STATUS_ENABLED;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.service.PeriodicalsService;
import ua.testing.periodicals.service.UsersService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PeriodicalsRepository periodicalsRepo;

    @Autowired
    private SubscriptionsRepository subscriptionRepo;

    @Autowired
    private UsersService userService;

    @Autowired
    private PeriodicalsService periodicalService;

    private static final Logger logger = LoggerFactory.getLogger(PeriodicalController.class);

    private String getCurrentUserName() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form.html";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(STATUS_ENABLED);
        Role userRole = roleRepo.findByName(ROLE_USER);
        user.setRoles(new HashSet<>(List.of(userRole)));
        Optional<String> fullName = Optional.ofNullable(user.getFullName());
        if (!fullName.isPresent()) {
            user.setFullName(user.getFirstName() + " " + user.getLastName());
        }

        userRepo.save(user);

        return "register_success";
    }

    @RequestMapping("/periodical/subscribe/{user_id}/{periodical_id}")
    public String subscribePeriodical(@PathVariable(name = "user_id") Long userId,
                                      @PathVariable(name = "periodical_id") Long periodicalId) throws DBException {
        logger.info("subscribePeriodical");

        Optional<User> user = userRepo.getUserByUserId(userId);
        Optional<Periodical> periodical = periodicalsRepo.getPeriodicalByPeriodicalId(periodicalId);

        logger.info("periodical ID ==> " + periodicalId);

        if (!user.isPresent()) {
            throw new DBException("To subscribe, please sign in!");
        } else {
            if (userService.checkSubscription(userId, periodicalId)) {
                throw new DBException("You are already subscribed");
            }

            if (periodical.isPresent()) {
                if (user.get().getBalance() < periodical.get().getPrice()) {
                    throw new DBException("You don't have enough account balance");
                }

                userService.updateBalance(user.get(), periodical.get().getPrice());

                LocalDate startDate = LocalDate.now();
                logger.info("subscription startDate = " + startDate);

                LocalDate endDate = LocalDate.now().plusYears(1);
                logger.info("subscription endDate = " + endDate);

                periodicalService.subscribe(periodicalId, userId, startDate, endDate);
            }
        }

        return "redirect:/periodicals";
    }

    @GetMapping("/user/replenish_account")
    public String replenishAccount(Model model) {
        String username = getCurrentUserName();
        Optional<User> user = userRepo.getUserByUsername(username);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "user/replenish_account.html";
    }

    @Transactional
    @PostMapping("/user/process_replenish")
    public String processReplenish(User user) {
        userRepo.updateBalance(user.getBalance(), user.getEmail());
        return "user/replenish_success.html";
    }
}