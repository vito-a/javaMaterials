package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Subscription;

import java.util.List;

@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscription, Long> {
    Subscription findBySubId(Long subId);
    @Query("SELECT s FROM Subscription s " +
           "LEFT JOIN User u ON u.userId = s.userId "+
           "WHERE u.username = ?1")
    List<Subscription> findMySubscriptions(String username);
    Subscription getByUserIdAndSubId(long userId, long subId);
}
