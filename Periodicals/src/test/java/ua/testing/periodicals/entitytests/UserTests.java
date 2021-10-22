package ua.testing.periodicals.entitytests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

/**
 * The Periodicals users tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserTests {

    private TestEntityManager entityManager;

    @Autowired
    private UserRepository usersRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Test
    /**
     * Test user entity.
     */
    public void testUserEntity() {
        User user = new User();

        assertNotNull(user);

        user.setUserId(100L);
        user.setFirstName("Test first name 1");
        user.setLastName("Test last name 1");
        user.setEmail("test1@gmail.com");
        user.setBalance(1000.0);

        user.setEnabled(STATUS_ENABLED);
        user.setAccountNonLocked(true);
        user.setLockTime(null);
        user.setFailedAttempt(0);
        Role userRole = roleRepo.findByName(ROLE_USER);
        user.setRoles(new HashSet<>(List.of(userRole)));

        assertEquals(user.getUserId(), 100L, 0);
        assertEquals(user.getFirstName(), "Test first name 1");
        assertEquals(user.getEmail(), "test1@gmail.com");
        assertEquals(user.getBalance(), 1000.0,0);
        assertEquals(user.getRoles(), new HashSet<>(List.of(userRole)));
        assertEquals(user.toString(),"User [login=Test first name 1,balance=10000,email=test1@gmail.com,userRole=1]");
    }

    /**
     * Test user creation.
     */
    @Test
    public void testUserCreation() {
        User user = new User();
        user.setEmail("test2@gmail.com");
        user.setPassword("test2021");
        user.setFirstName("Test first name 2");
        user.setLastName("Test last name 2");

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

        User existingUser = entityManager.find(User.class, savedUser.getUserId());

        assertThat(user.getEmail()).isEqualTo(existingUser.getEmail());
    }
}

