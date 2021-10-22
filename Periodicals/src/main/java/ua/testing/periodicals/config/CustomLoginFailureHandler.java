package ua.testing.periodicals.config;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import ua.testing.periodicals.controller.PeriodicalController;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.UserRepository;
import ua.testing.periodicals.service.UsersService;

/**
 * The Custom login failure handler.
 */
@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UsersService userService;

    @Autowired
    private UserRepository userRepo;

    private static final Logger logger = LoggerFactory.getLogger(PeriodicalController.class);

    /**
     * The AuthenticationFailure event handler.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        User user = userRepo.getUserByUsername(request.getParameter("user")).orElseGet(User::new);
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        Locale locale = LocaleContextHolder.getLocale();
        String message = userService.checkFailedAttempt(user).equals(userService.getMaxFailedAttempts()) ? messageSource.getMessage("user.locked", null, locale) : "";
        message = message.isEmpty() && !user.isAccountNonLocked() && (user.getLockTime() != null) && userService.unlockWhenTimeExpired(user) ?
                messageSource.getMessage("user.unlocked", null, locale) : "";

        exception = new LockedException(message);
        logger.error(message);

        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }

}