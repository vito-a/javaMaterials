package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.periodicals.model.entity.Role;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.repository.RoleRepository;
import ua.testing.periodicals.repository.UserRepository;

import static ua.testing.periodicals.model.constants.Constants.ROLE_USER;
import static ua.testing.periodicals.model.constants.Constants.STATUS_ENABLED;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.service.UsersService;

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
    private UsersService usersService;

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

    @RequestMapping("/user/new")
    public String showNewUserForm(Model model) {
        User User = new User();
        model.addAttribute("User", User);

        return "new_user.html";
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("User") User user) {
        usersService.save(user);

        return "redirect:/";
    }

    @RequestMapping("/user/edit/{id}")
    public ModelAndView showEditUserForm(@PathVariable(name = "id") Long userId) {
        ModelAndView mav = new ModelAndView("user/edit_user.html");

        User user = userRepo.getUserByUserId(userId);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long userId) {
        usersService.delete(userId);

        return "redirect:/";
    }
}