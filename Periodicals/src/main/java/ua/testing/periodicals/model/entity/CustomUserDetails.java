package ua.testing.periodicals.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.testing.periodicals.controller.PeriodicalController;

/**
 * The Custom user details.
 */
public class CustomUserDetails implements UserDetails {
 
    private final User user;

    /**
     * Instantiates a new custom Spring Security User Details.
     *
     * @param user the user
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    private static final Logger logger = LoggerFactory.getLogger(PeriodicalController.class);

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
 
    @Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getUsername();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() { return user.isEnabled(); }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        if ((user.getFullName() == null) || user.getFullName().isEmpty()) {
            return user.getFirstName() + " " + user.getLastName();
        }
        return user.getFullName();
    }
 
}