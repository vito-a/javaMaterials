package ua.training.model.dao;

import ua.training.model.entity.Subscription;

import java.util.List;

/**
 * The Subscription DAO interface.
 */
public interface SubscriptionDao extends GenericDao<Subscription> {
    /**
     * Find my subscriptions list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Subscription> findMySubscriptions(Long userId);
}
