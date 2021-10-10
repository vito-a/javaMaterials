package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Subscription;

import java.util.Optional;

@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscription, Long> {
    Subscription findBySubId(Long subId);
}
