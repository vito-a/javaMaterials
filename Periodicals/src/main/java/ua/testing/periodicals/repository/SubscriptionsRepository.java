package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscription, Long> {
    Subscription findBySubId(Long subId);
    /*
    @Query("SELECT s FROM Subscription s WHERE u.username = ?1")
    List<Subscription> findMySubscriptions(String username);
     */
}
