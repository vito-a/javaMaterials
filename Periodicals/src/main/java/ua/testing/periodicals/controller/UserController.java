package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.model.constants.Constants;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.service.UsersService;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

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
        user.setRole(Constants.ROLE_ID_USER);
        user.setStatus(Constants.STATUS_ACTIVE);
        user.setFullname(user.getFirstname() + " " + user.getLastname());
        user.setLogin(user.getEmail());

        userRepo.save(user);

        return "register_success";
    }

    @RequestMapping("/user/new")
    public String showNewUserForm(Model model) {
        User User = new User();
        model.addAttribute("User", User);

        return "new_periodical.html";
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("User") User user) {
        usersService.save(user);

        return "redirect:/";
    }

    @RequestMapping("/user/edit/{id}")
    public ModelAndView showEditUserForm(@PathVariable(name = "id") Long userId) {
        ModelAndView mav = new ModelAndView("edit_user");

        User User = usersService.get(userId);
        mav.addObject("User", User);

        return mav;
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long userId) {
        usersService.delete(userId);

        return "redirect:/";
    }
}