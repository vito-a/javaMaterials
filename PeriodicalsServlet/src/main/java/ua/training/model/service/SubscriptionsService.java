package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SubscriptionDao;
import ua.training.model.entity.Subscription;

import java.util.List;

/**
 * The Subscriptions service.
 */
public class SubscriptionsService {

    /**
     * The Dao factory.
     */
    DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * Gets all subscriptions.
     *
     * @return the all subscriptions
     */
    public List<Subscription> getAllSubscriptions() {
        try (SubscriptionDao dao = daoFactory.createSubscriptionDao()) {
            return dao.findAll();
        }
    }

    /**
     * Gets my subscriptions.
     *
     * @param userId the user id
     * @return the my subscriptions
     */
    public List<Subscription> getMySubscriptions(Long userId) {
        try (SubscriptionDao dao = daoFactory.createSubscriptionDao()) {
            return dao.findMySubscriptions(userId);
        }
    }
}
