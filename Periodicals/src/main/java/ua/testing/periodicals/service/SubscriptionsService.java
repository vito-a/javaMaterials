package ua.testing.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.repository.SubscriptionsRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionsService {
    @Autowired
    private SubscriptionsRepository subscriptionsRepo;

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionsService.class);

    public List<Subscription> listAll(String date_keyword) {
        Optional<String> optionalDateKeyword = Optional.ofNullable(date_keyword);
        List<Subscription> subscriptionList = subscriptionsRepo.findAll();

        if (optionalDateKeyword.isPresent()) {
            return search(optionalDateKeyword.get());
        }

        return subscriptionList;
    }

    public List<Subscription> search(String date_keyword) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Subscription> subscriptionList;

        LocalDate myDate = LocalDate.parse(date_keyword);
        String query = "SELECT s FROM Subscription s WHERE s.startdate >= :startdate";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("subscriptions");
        EntityManager entityManager = factory.createEntityManager();
        subscriptionList = entityManager.createQuery(query, Subscription.class)
                .setParameter("startdate", myDate)
                .getResultList();
        entityManager.close();
        factory.close();

        return subscriptionList;
    }

    public void save(Subscription subscription) {
        try {
            subscriptionsRepo.save(subscription);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("Cannot save subscription " + subscription.getSubId());
            logger.error(e.getMessage());
        }
    }
}