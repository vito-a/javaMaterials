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
import java.util.List;

@Service
public class SubscriptionsService {
    @Autowired
    private SubscriptionsRepository subscriptionsRepo;

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionsService.class);

    public List<Subscription> listAll(String date_keyword) {
        if (date_keyword != null) {
            return search(date_keyword);
        }
        return subscriptionsRepo.findAll();
    }

    // TODO: deprecated? switch to LocalDate
    // TODO: Learn LocalDateTime
    // TODO: make try with resource
    public List<Subscription> search(String date_keyword) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date myDate = null;
        List<Subscription> subscriptionList = null;

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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return subscriptionList;
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