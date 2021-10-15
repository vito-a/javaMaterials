package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.periodicals.model.entity.Role;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.repository.RoleRepository;
import ua.testing.periodicals.repository.SubscriptionsRepository;
import ua.testing.periodicals.repository.UserRepository;

import static ua.testing.periodicals.model.constants.Constants.ROLE_USER;
import static ua.testing.periodicals.model.constants.Constants.STATUS_ENABLED;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.service.UsersService;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private SubscriptionsRepository subscriptionRepo;

    @Autowired
    private UsersService usersService;

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
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        if ((user.getFullName() == null) || user.getFullName().isEmpty()) {
            user.setFullName(user.getFirstName() + " " + user.getLastName());
        }

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/user/replenish_account")
    public String replenishAccount(Model model) {
        String username = getCurrentUserName();
        User user = userRepo.getUserByUsername(username);
        model.addAttribute("user", user);

        return "user/replenish_account.html";
    }

    @Transactional
    @PostMapping("/user/process_replenish")
    public String processReplenish(User user) {
        userRepo.updateBalance(user.getBalance(), user.getEmail());

        return "user/replenish_success.html";
    }
}