package ua.testing.periodicals.service;

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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The Periodicals service.
 */
@Service
public class PeriodicalsService {
    @Autowired
    private PeriodicalsRepository periodicalsRepo;

    @Autowired
    private SubscriptionsService subscriptionsService;

    @Value("${periodicals.pageSize:5}")
    private int pageSize;

    private static final Logger logger = LoggerFactory.getLogger(PeriodicalsService.class);

    /**
     * List all periodicals.
     *
     * @param keyword the search keyword
     * @return the periodicals list
     */
    public List<Periodical> listAll(String keyword) {
        Optional<String> optionalKeyword = Optional.ofNullable(keyword);
        if (optionalKeyword.isPresent()) {
            return periodicalsRepo.search(optionalKeyword.get());
        }
        return periodicalsRepo.findAll();
    }

    /**
     * List all periodicals page.
     *
     * @param pageNum   the page num
     * @param sortField the sort field
     * @param sortDir   the sort dir
     * @return the periodical paginated object
     */
    public Page<Periodical> listAll(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );

        return periodicalsRepo.findAll(pageable);
    }

    /**
     * Save periodical.
     *
     * @param periodical the periodical object
     */
    public void save(Periodical periodical) {
        try {
            periodicalsRepo.save(periodical);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("Cannot save periodical " + periodical.getPeriodicalId());
            logger.error(e.getMessage());
        }
    }

    /**
     * Subscribe.
     *
     * @param periodicalId the periodical id
     * @param userId       the user id
     * @param startDate    the start date
     * @param endDate      the end date
     * @throws DBException the DB exception
     */
    public void subscribe(Long periodicalId, Long userId, LocalDate startDate, LocalDate endDate) throws DBException {
        try {
            Subscription subscription = new Subscription(userId, periodicalId, startDate, endDate);
            subscriptionsService.save(subscription);
        } catch (Exception e) {
            logger.error("Cannot create subscription with params (periodicalId, userId, startDate, endDate) ==> " +
                    "(" + periodicalId + "," + userId + "," + startDate + "," + endDate + ")", e);
            throw new DBException("Cannot create subscription", e);
        }
    }

    /**
     * Get optional.
     *
     * @return the optional periodical object
     */
    public Optional<Periodical> get() {
        return Optional.of(new Periodical());
    }

    /**
     * Get optional.
     *
     * @param id the id
     * @return the optional periodical object
     */
    public Optional<Periodical> get(Long id) {
        return periodicalsRepo.findById(id);
    }

    /**
     * Delete.
     *
     * @param id the periodical id
     */
    public void delete(Long id) {
        periodicalsRepo.deleteById(id);
    }
}