package ua.testing.periodicals;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.testing.periodicals.model.constants.Constants.ROLE_USER;
import static ua.testing.periodicals.model.constants.Constants.STATUS_ENABLED;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import ua.testing.periodicals.model.entity.Role;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.RoleRepository;
import ua.testing.periodicals.repository.UserRepository;

import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class PeriodicalsApplicationTests {

    private TestEntityManager entityManager;

    @Autowired
    private UserRepository usersRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test1@gmail.com");
        user.setPassword("test2021");
        user.setFirstName("Test first name");
        user.setLastName("Test last name");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(STATUS_ENABLED);
        user.setAccountNonLocked(true);
        user.setLockTime(null);
        user.setFailedAttempt(0);
        Role userRole = roleRepo.findByName(ROLE_USER);
        user.setRoles(new HashSet<>(List.of(userRole)));

        User savedUser = usersRepo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getUserId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
}
