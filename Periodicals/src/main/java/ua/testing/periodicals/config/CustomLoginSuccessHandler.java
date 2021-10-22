package ua.testing.periodicals.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ua.testing.periodicals.model.entity.CustomUserDetails;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.service.UsersService;

/**
 * The Custom login success handler.
 */
@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UsersService userService;

    @Autowired
    private UserRepository userRepo;

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    /**
     * The AuthenticationSuccess event handler.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails =  (CustomUserDetails) authentication.getPrincipal();
        Optional<User> user = userRepo.getUserByUsername(userDetails.getUsername());
        int result = user.isPresent() && (user.get().getFailedAttempt() > 0) ? userService.resetFailedAttempts(user.get().getUsername()) : -1;
        String message = result > 0 ? "onAuthenticationSuccess" + user.get().getUsername() : "";
        logger.info(message);

        super.onAuthenticationSuccess(request, response, authentication);
    }

}