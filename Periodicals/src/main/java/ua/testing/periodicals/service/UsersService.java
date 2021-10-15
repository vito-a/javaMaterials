package ua.testing.periodicals.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.testing.periodicals.controller.PeriodicalController;
import ua.testing.periodicals.model.dao.DBException;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.SubscriptionsRepository;
import ua.testing.periodicals.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UserRepository usersRepo;

    @Autowired
    private SubscriptionsRepository subscriptionsRepo;

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    public List<User> listAll(String keyword) {
        if (keyword != null) {
            return usersRepo.search(keyword);
        }
        return usersRepo.findAll();
    }

    public void save(User user) {
        usersRepo.save(user);
    }

    public User get(Long userId) {
        return usersRepo.findById(userId).get();
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
}