package ua.training.controller.commands.admin;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Student;
import ua.training.model.entity.User;
import ua.training.model.service.StudentService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UsersList implements Command {

    UserService userService;

    public UsersList(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users" , users);
        return "/WEB-INF/userlist.jsp";
    }
}
