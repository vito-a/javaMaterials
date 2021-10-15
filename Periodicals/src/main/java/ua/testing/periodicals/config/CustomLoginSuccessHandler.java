package ua.testing.periodicals.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ua.testing.periodicals.model.entity.CustomUserDetails;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.service.UsersService;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UsersService userService;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails =  (CustomUserDetails) authentication.getPrincipal();
        User user = userRepo.getUserByUsername(userDetails.getUsername());
        System.out.println(user);
        if (user.getFailedAttempt() > 0) {
            userService.resetFailedAttempts(user.getUsername());
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

}