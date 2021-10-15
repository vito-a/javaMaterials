package ua.testing.periodicals.service;

import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.testing.periodicals.model.dao.DBException;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.repository.PeriodicalsRepository;

import javax.persistence.Column;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static ua.testing.periodicals.model.constants.Constants.PERIODICAL_ID;
import static ua.testing.periodicals.model.constants.Constants.USER_ID;

@Service
public class PeriodicalsService {
    @Autowired
    private PeriodicalsRepository periodicalsRepo;

    @Autowired
    private SubscriptionsService subscriptionsService;

    @Value("${periodicals.pageSize:5}")
    private int pageSize;

    private static final Logger logger = LoggerFactory.getLogger(PeriodicalsService.class);

    public List<Periodical> listAll(String keyword) {
        if (keyword != null) {  // TODO: Optional
            return periodicalsRepo.search(keyword);
        }
        return periodicalsRepo.findAll();  // TODO: Optional
    }

    public Page<Periodical> listAll(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );

        return periodicalsRepo.findAll(pageable);
    }

    // TODO: try - catch
    public void save(Periodical periodical) {
        try {
            periodicalsRepo.save(periodical);
        } catch (JDBCException e) {
            System.out.println(e.getErrorCode());
            logger.error("Cannot save periodical " + periodical.getPeriodicalId());
            logger.error(e.getMessage());
        }
    }

    public void subscribe(Long periodicalId, Long userId, Date startDate, Date endDate) throws DBException {
        try {
            Subscription subscription = new Subscription(userId, periodicalId, startDate, endDate);
            subscriptionsService.save(subscription);
        } catch (Exception e) {
            logger.error("Cannot create subscription with params (periodicalId, userId, startDate, endDate) ==> " +
                    "(" + periodicalId + "," + userId + "," + startDate + "," + endDate + ")", e);
            throw new DBException("Cannot create subscription", e);
        }
    }

    // TODO: IsPresent()
    // TODO: check warnings
    public Periodical get(Long id) {
        return periodicalsRepo.findById(id).get();
    }

    public void delete(Long id) {
        periodicalsRepo.deleteById(id);
    }
}