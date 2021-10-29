package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.PeriodicalDao;
import ua.training.model.entity.Subscription;

import java.util.List;

/**
 * The Subscriptions service.
 */
public class SubscriptionsService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Subscription> getAllSubscriptions() {
        /*
        try (PeriodicalDao dao = daoFactory.createSubscriptionDao()) {
            return dao.findAll();
        }
         */
        return null;
    }
}
