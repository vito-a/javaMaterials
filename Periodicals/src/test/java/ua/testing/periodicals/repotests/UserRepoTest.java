package ua.testing.periodicals.repotests;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import ua.testing.periodicals.model.entity.Role;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.RoleRepository;
import ua.testing.periodicals.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static ua.testing.periodicals.model.constants.Constants.ROLE_USER;
import static ua.testing.periodicals.model.constants.Constants.STATUS_ENABLED;

/**
 * The User repo test.
 */
public class UserRepoTest {

    @Autowired
    private UserRepository usersRepo;

    @Autowired
    private RoleRepository roleRepo;

    /**
     * Test user repo.
     */
    @Test
    public void testUserRepo() {

        Optional<User> userById = usersRepo.getUserByUserId(1L);
        Optional<User> userByEmail = usersRepo.getUserByEmail("user@gmail.com");
        List<User> accounts = usersRepo.findAll();
        List<User> accounts1 = usersRepo.search("user1");
        User user = new User();
        user.setFirstName("Test first name 1");
        user.setEmail("test1@gmail.com");
        user.setEnabled(STATUS_ENABLED);
        user.setAccountNonLocked(true);
        user.setLockTime(null);
        user.setFailedAttempt(0);
        Role userRole = roleRepo.findByName(ROLE_USER);
        user.setRoles(new HashSet<>(List.of(userRole)));

        usersRepo.save(user);
        // user.setEmail("test2@gmail.com");
        // usersRepo.save(user);

        Assert.assertEquals(userById.get().getFirstName(), "Sami");
        Assert.assertEquals(userByEmail.get().getUserId(), 5L, 0);
        Assert.assertEquals(accounts.get(0).getFirstName(), "Sami");
        Assert.assertEquals(accounts1.get(0).getFirstName(), "User");
        Assert.assertEquals(user.getFirstName(), "Test first name 1");
        Assert.assertEquals(user.getEmail(), "test1@gmail.com");
    }
}
