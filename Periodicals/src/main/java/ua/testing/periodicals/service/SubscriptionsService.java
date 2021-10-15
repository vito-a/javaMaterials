package ua.testing.periodicals.service;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.SubscriptionsRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionsService {
    @Autowired
    private SubscriptionsRepository subscriptionsRepo;

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionsService.class);

    public List<Subscription> listAll(String date_keyword) throws ParseException {
        Optional<String> optionalDateKeyword = Optional.ofNullable(date_keyword);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Subscription> subscriptionList = subscriptionsRepo.findAll();
        List<Subscription> updatedList = new java.util.ArrayList<>(List.of());

        if (optionalDateKeyword.isPresent()) {
            return search(optionalDateKeyword.get());
        }

        for (Subscription subscription : subscriptionList) {
            subscription.setEnddate(format.parse(subscription.getEnddate().toString()));
            subscription.setStartdate(format.parse(subscription.getStartdate().toString()));
            updatedList.add(subscription);
        }
        
        return updatedList;
    }

    // TODO: deprecated? switch to LocalDate
    // TODO: Learn LocalDateTime
    // TODO: make try with resource
    public List<Subscription> search(String date_keyword) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date myDate = null;
        List<Subscription> subscriptionList = null;
        List<Subscription> updatedList = new java.util.ArrayList<>(List.of());

        // TODO: make try with resource
        try {
            myDate = format.parse(date_keyword);
            String query = "SELECT s FROM Subscription s WHERE s.startdate >= :startdate";
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("subscriptions");
            EntityManager entityManager = factory.createEntityManager();
            subscriptionList = entityManager.createQuery(query, Subscription.class)
                    .setParameter("startdate", sqlDate)
                    .getResultList();
            entityManager.close();
            factory.close();
            for (Subscription subscription : subscriptionList) {
                subscription.setEnddate(format.parse(subscription.getEnddate().toString()));
                subscription.setStartdate(format.parse(subscription.getStartdate().toString()));
                updatedList.add(subscription);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return updatedList;
    }

    // TODO: try - catch
    public void save(Subscription subscription) {
        try {
            subscriptionsRepo.save(subscription);
        } catch (JDBCException e) {
            System.out.println(e.getErrorCode());
            logger.error("Cannot save subscription " + subscription.getSubId());
            logger.error(e.getMessage());
        }
    }
}