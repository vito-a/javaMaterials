package ua.testing.periodicals.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.testing.periodicals.model.dao.DBException;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.SubscriptionsRepository;
import ua.testing.periodicals.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UserRepository usersRepo;

    @Autowired
    private SubscriptionsRepository subscriptionsRepo;

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    public static final int MAX_FAILED_ATTEMPTS = 3;

    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000;

    public List<User> listAll(String keyword) {
        Optional<String> optionalKeyword = Optional.ofNullable(keyword);
        if (optionalKeyword.isPresent()) {
            return usersRepo.search(optionalKeyword.get());
        }
        return usersRepo.findAll();
    }

    public void save(User user) {
        usersRepo.save(user);
    }

    public User get(Long userId) {
        return usersRepo.findById(userId).orElseGet(User::new);
    }

    public void delete(Long userId) {
        usersRepo.deleteById(userId);
    }

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

    @ModelAttribute("userId")
    public void updateBalance(User user, Long price) {
        user.setBalance(user.getBalance() - price);
        usersRepo.save(user);
    }

    public int increaseFailedAttempts(User user) {
        int newFailAttempts = user.getFailedAttempt() + 1;
        usersRepo.updateFailedAttempts(newFailAttempts, user.getEmail());

        return newFailAttempts;
    }

    public void resetFailedAttempts(String email) {
        usersRepo.updateFailedAttempts(0, email);
    }

    public int lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
        usersRepo.save(user);
        return MAX_FAILED_ATTEMPTS;
    }

    public boolean unlockWhenTimeExpired(User user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);
            usersRepo.save(user);
            return true;
        }

        return false;
    }

    public Optional<User> getUserByEmail(String email) {
        return usersRepo.getUserByEmail(email);
    }
}