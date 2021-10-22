package ua.testing.periodicals.service;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.testing.periodicals.model.dao.DBException;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.SubscriptionsRepository;
import ua.testing.periodicals.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The Users service.
 */
@Getter
@Service
public class UsersService {
    @Autowired
    private UserRepository usersRepo;

    @Autowired
    private SubscriptionsRepository subscriptionsRepo;

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Value("${periodicals.max_failed_attempts:3}")
    private int maxFailedAttempts;

    @Value("${periodicals.lock_time_duration:3600000L}")
    private long lockTimeDuration;

    /**
     * List all users.
     *
     * @param keyword the search keyword
     * @return the list
     */
    public List<User> listAll(String keyword) {
        Optional<String> optionalKeyword = Optional.ofNullable(keyword);
        if (optionalKeyword.isPresent()) {
            return usersRepo.search(optionalKeyword.get());
        }
        return usersRepo.findAll();
    }

    /**
     * Save user.
     *
     * @param user the user object
     */
    public void save(User user) {
        usersRepo.save(user);
    }

    /**
     * Get user by ID.
     *
     * @param userId the user id
     * @return the user object
     */
    public User get(Long userId) {
        return usersRepo.findById(userId).orElseGet(User::new);
    }

    /**
     * Delete.
     *
     * @param userId the user id
     */
    public void delete(Long userId) {
        usersRepo.deleteById(userId);
    }

    /**
     * Check subscription boolean.
     *
     * @param userId the user id
     * @param subId  the sub id
     * @return the boolean
     * @throws DBException the db exception
     */
    public boolean checkSubscription(long userId, long subId) throws DBException {
        boolean result;
        try {
            Subscription subscription = subscriptionsRepo.getByUserIdAndSubId(userId, subId);
            result = (subscription != null);
        } catch (Exception e) {
            logger.error("Cannot check subscription", e);
            throw new DBException("Cannot check subscription", e);
        }

        return result;
    }

    /**
     * Update balance.
     *
     * @param user  the user
     * @param price the balance price
     */
    @ModelAttribute("userId")
    public void updateBalance(User user, Long price) {
        user.setBalance(user.getBalance() - price);
        usersRepo.save(user);
    }

    /**
     * Increase failed attempts int.
     *
     * @param user the user
     * @return the failed attempts number
     */
    @Transactional
    public int increaseFailedAttempts(User user) {
        int newFailAttempts = user.getFailedAttempt() + 1;
        usersRepo.updateFailedAttempts(newFailAttempts, user.getEmail());

        return newFailAttempts;
    }

    /**
     * Reset the failed attempts count.
     *
     * @param email the user email
     * @return the reset failed attempts number
     */
    @Transactional
    public Integer resetFailedAttempts(String email) {
        usersRepo.updateFailedAttempts(0, email);

        return 0;
    }

    /**
     * Lock int.
     *
     * @param user the user object
     * @return the max failed attempts number for locked user
     */
    public int lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
        usersRepo.save(user);
        return maxFailedAttempts;
    }

    /**
     * Unlock when time expired boolean.
     *
     * @param user the user object
     * @return the locked/unlocked status
     */
    public boolean unlockWhenTimeExpired(User user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + lockTimeDuration < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);
            usersRepo.save(user);

            return true;
        }

        return false;
    }

    /**
     * Check failed attempt integer.
     *
     * @param user the user
     * @return the failed attempts number for a user
     *
     * TODO: check 2 requests in the method
     * TODO: do JavaDOCs like in Java
     */
    public Integer checkFailedAttempt(User user) {
        return user.isEnabled() && user.isAccountNonLocked() && (user.getFailedAttempt() < maxFailedAttempts - 1) ?
                increaseFailedAttempts(user) : lock(user);
    }

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user object
     */
    public Optional<User> getUserByEmail(String email) {
        return usersRepo.getUserByEmail(email);
    }
}