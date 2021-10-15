package ua.testing.periodicals.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.service.UsersService;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UsersService userService;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("user");
        System.out.println("error - " + username);
        Optional<User> user = userRepo.getUserByUsername(username);

        if (user.isPresent()) {
            if (user.get().isEnabled() && user.get().isAccountNonLocked()) {
                if (user.get().getFailedAttempt() < UsersService.MAX_FAILED_ATTEMPTS - 1) {
                    userService.increaseFailedAttempts(user.get());
                } else {
                    userService.lock(user.get());
                    exception = new LockedException("Your account has been locked due to 3 failed attempts."
                            + " It will be unlocked after 24 hours.");
                }
            } else if (!user.get().isAccountNonLocked()) {
                if ((user.get().getLockTime() != null) && userService.unlockWhenTimeExpired(user.get())) {
                    exception = new LockedException("Your account has been unlocked. Please try to login again.");
                }
            }

        }

        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }

}