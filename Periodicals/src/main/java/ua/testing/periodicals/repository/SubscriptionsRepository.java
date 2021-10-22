package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Subscription;

import java.util.List;

/**
 * The Subscriptions repository.
 */
@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscription, Long> {
    /**
     * Find by sub id subscription.
     *
     * @param subId the sub id
     * @return the subscription object
     */
    Subscription findBySubId(Long subId);

    /**
     * Find my subscriptions list.
     *
     * @param username the username
     * @return the subscriptions list
     */
    @Query("SELECT s FROM Subscription s " +
           "LEFT JOIN User u ON u.userId = s.userId "+
           "WHERE u.username = ?1")
    List<Subscription> findMySubscriptions(String username);

    /**
     * Gets by user id and sub id.
     *
     * @param userId the user id
     * @param subId  the sub id
     * @return the by user id and sub id
     */
    Subscription getByUserIdAndSubId(long userId, long subId);
}
