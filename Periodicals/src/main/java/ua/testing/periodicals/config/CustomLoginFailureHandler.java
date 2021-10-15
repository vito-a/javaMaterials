package ua.testing.periodicals.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.service.UsersService;
import static ua.testing.periodicals.service.UsersService.MAX_FAILED_ATTEMPTS;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UsersService userService;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // TODO: several requests to database
        User user = userRepo.getUserByUsername(request.getParameter("user")).orElseGet(User::new);
        Integer result = user.isEnabled() && user.isAccountNonLocked() && (user.getFailedAttempt() < MAX_FAILED_ATTEMPTS - 1) ?
                userService.increaseFailedAttempts(user) : userService.lock(user);

        // TODO: strings to resources
        String message = result.equals(MAX_FAILED_ATTEMPTS) ? "Your account is locked for 24 hours due to 3 failed attempts." : "";
        message = message.isEmpty() && !user.isAccountNonLocked() && (user.getLockTime() != null) && userService.unlockWhenTimeExpired(user) ?
                "Your account has been unlocked. Please try to login again." : "";

        exception = new LockedException(message);

        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }

}