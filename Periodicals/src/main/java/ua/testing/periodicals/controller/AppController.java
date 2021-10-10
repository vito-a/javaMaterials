package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.model.constants.Constants;
import ua.testing.periodicals.model.entity.User;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "index.html";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form.html";
    }

    @GetMapping("/login")
    public String get(Model model) {
        model.addAttribute("title", "Форма входа");
        return "login";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Constants.ROLE_ID_USER);
        user.setStatus(Constants.STATUS_ACTIVE);
        user.setFullname(user.getFirstname() + " " + user.getLastname());
        user.setLogin(user.getEmail());

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users.html";
    }
}