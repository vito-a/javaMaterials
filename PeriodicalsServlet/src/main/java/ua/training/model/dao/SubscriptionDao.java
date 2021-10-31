package ua.training.model.dao;

import ua.training.model.entity.Subscription;

import java.util.List;


public interface SubscriptionDao extends GenericDao<Subscription> {
    List<Subscription> findMySubscriptions(Long userId);
}
