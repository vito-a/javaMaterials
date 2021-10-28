package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import java.security.AuthProvider;
import java.util.Optional;


public class Login implements Command {

    private UserService userService;

    private final Logger logger = LogManager.getLogger(Login.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        if( name == null || name.equals("") || pass == null || pass.equals("")  ){
            logger.info("Login failure (name, pass) ==> " +
                    "(" + name + ", " + pass + ")");
            return "/login.jsp";
        }

        if (CommandUtility.checkUserIsLogged(request, name)) {
            logger.info("Already logged in (name, pass) ==> " +
                    "(" + name + ", " + pass + ")");
            return "/WEB-INF/error.jsp";
        }

        //todo: check login with DB
        Optional<User> user = userService.login(name);
        if (user.isPresent() && user.get().getPassHash() == pass.hashCode()) {
            request.getSession().setAttribute("user" , user.get());
            logger.info("Login success (name, pass) ==> " +
                    "(" + name + ", " + pass + ")");
            logger.info("User " + name + " is logged in.");
        } else {
            logger.info("Invalid attempt of user login:'" + name + "'");
//            throw new DBException("Incorrect login or Password! Please, enter correct data");
            return "/login.jsp";
        }

        if (name.equals("Admin")) {
            CommandUtility.setUserRole(request, User.ROLE.ADMIN, name);
            return "/WEB-INF/admin/adminbasis.jsp";
//            return "/WEB-INF/studentlist.jsp";
        } else if(name.equals("User")) {
            CommandUtility.setUserRole(request, User.ROLE.USER, name);
            return "/WEB-INF/user/userbasis.jsp";
        } else {
            CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, name);
            return "/login.jsp";
        }
    }

}