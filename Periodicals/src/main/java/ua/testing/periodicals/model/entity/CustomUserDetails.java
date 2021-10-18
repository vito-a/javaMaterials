package ua.testing.periodicals.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.testing.periodicals.controller.PeriodicalController;

public class CustomUserDetails implements UserDetails {
 
    private final User user;
     
    public CustomUserDetails(User user) {
        this.user = user;
    }

    private static final Logger logger = LoggerFactory.getLogger(PeriodicalController.class);

    // change For (Role to stream
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            logger.info(role.getName());
        }

        return authorities;
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
     
    public String getFullName() {
        if ((user.getFullName() == null) || user.getFullName().isEmpty()) {
            return user.getFirstName() + " " + user.getLastName();
        }
        return user.getFullName();
    }
 
}