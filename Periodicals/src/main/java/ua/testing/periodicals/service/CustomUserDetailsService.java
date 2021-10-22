package ua.testing.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.model.entity.CustomUserDetails;
import ua.testing.periodicals.repository.UserRepository;

import java.util.Optional;

/**
 * The custom Spring User Details service.
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.getUserByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user.get());
    }

}