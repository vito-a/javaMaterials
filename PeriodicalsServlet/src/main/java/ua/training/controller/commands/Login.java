package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.mindrot.jbcrypt.BCrypt;
import ua.training.model.entity.User;
import ua.training.model.service.BCryptService;
import ua.training.model.service.UserService;

import java.security.AuthProvider;
import java.util.Optional;
import java.util.function.Function;


public class Login implements Command {

    private UserService userService;

    private final Logger logger = LogManager.getLogger(Login.class.getName());

    private static final BCryptService bcrypt = new BCryptService(10);
    private String[] mutableHash = new String[1];
    Function<String, Boolean> update = hash -> { mutableHash[0] = hash; return true; };

    public static String hash(String password) {
        return bcrypt.hash(password);
    }

    public static boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
        return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
    }

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        if( name == null || name.equals("") || pass == null || pass.equals("")  ){
            logger.info("Login attempt (name, pass) ==> " +
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
        // user.get().getPassHash() == pass.hashCode()
        String newHash = BCrypt.hashpw(pass, BCrypt.gensalt(10));
        if (user.isPresent() && bcrypt.verifyAndUpdateHash(pass, user.get().getPassword(), update)) {
            request.getSession().setAttribute("user" , user.get());
            logger.info("Login success (name, pass) ==> " +
                    "(" + name + ", " + pass + ")");
            logger.info("User " + name + " is logged in.");
        } else {
            logger.info("Invalid attempt of user login:'" + name + "'");
            return "/login.jsp";
        }

        if (name.equals("admin")) {
            CommandUtility.setUserRole(request, User.ROLE.ADMIN, name);
            return "redirect:/app/admin/users";
//            return "/WEB-INF/userlist.jsp";
        } else if(name.equals("user")) {
            CommandUtility.setUserRole(request, User.ROLE.USER, name);
            return "redirect:/app/user";
        } else {
            CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, name);
            return "/login.jsp";
        }
    }

}