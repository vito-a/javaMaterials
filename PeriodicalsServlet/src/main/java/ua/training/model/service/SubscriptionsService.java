package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SubscriptionDao;
import ua.training.model.entity.Subscription;

import java.util.List;

/**
 * The Subscriptions service.
 */
public class SubscriptionsService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Subscription> getAllSubscriptions() {
        try (SubscriptionDao dao = daoFactory.createSubscriptionDao()) {
            return dao.findAll();
        }
    }

    public List<Subscription> getMySubscriptions(Long userId) {
        try (SubscriptionDao dao = daoFactory.createSubscriptionDao()) {
            return dao.findMySubscriptions(userId);
        }
    }
}
