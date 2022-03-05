package ua.testing.periodicals.service;

import org.hibernate.HibernateException;
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
 * TODO: cover listAll
 */
@Service
public class PeriodicalsService {
    @Autowired
    private PeriodicalsRepository periodicalsRepository;

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
     *
     * TODO: Optional is not needed here as findAll() won't return null
     * TODO: what to test: 2 unit tests - one without params pass the null there
     * TODO: check that periodicalsRepo.findAll() will not be called in that case
     * TODO: and second unit test with non-null parameter
     * TODO: check that periodicalsRepo.findAll() will be called in that case
     * TODO: abbreviation like periodicalsRepo is a bad practice, write full
     * TODO: autowire through field is a bad practice
     * TODO: if you autowire through constructor you can pass additional params like other repository
     * TODO: for tests you can pass mocked repositories there
     * TODO: if you do only null param passing, it will be only 50% of coverage
     * TODO: Crtl+Shift+T - IDEA generates the test class
     * TODO: listAll must check that Pageable that went to finaAll is that one
     * TODO: the whole idea
     */
    public List<Periodical> listAll(String keyword) throws DBException {
        List<Periodical> result = null;
        try {
            result = (keyword != null) && !keyword.isEmpty() ? periodicalsRepository.search(keyword) : periodicalsRepository.findAll();
        } catch (NullPointerException e) {
            logger.error("listAll(String keyword) - cannot list periodicals for: " + keyword);
            throw new DBException("Cannot list periodicals", e);
        }

        return result;
    }

    /**
     * List all periodicals page.
     *
     * @param pageNum   the page num
     * @param sortField the sort field
     * @param sortDir   the sort dir
     * @return the periodical paginated object
     *
     * TODO: what to test: 2 unit tests - one without params pass the null there
     * TODO: check that periodicalsRepo.findAll() will be called in that case
     * TODO: and second unit test with non-null parameter
     * TODO: check that periodicalsRepo.findAll() will be called in that case
     * TODO: abbreviation like periodicalsRepo is a bad practice, write full
     * TODO: autowire through field is a bad practice
     * TODO: if you autowire through constructor you can pass additional params like other repository
     * TODO: for tests you can pass mocked repositories there
     * TODO: if you do only null param passing, it will be only 50% of coverage
     * TODO: Crtl+Shift+T - IDEA generates the test class
     * TODO: listAll must check that Pageable that went to finaAll is that one
     * TODO: the whole idea
     */
    public Page<Periodical> listAll(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );

        return periodicalsRepository.findAll(pageable);
    }

    /**
     * Save periodical.
     *
     * @param periodical the periodical object
     * TODO: 2 cases with Exception and without
     * TODO: if Repo.save throws Exception and service should not throw exception
     * TODO: catching the general Exception is a bad practice
     * TODO: add Throws particular exception
     * TODO: add the particular DBException and other exceptions
     * TODO: process exceptions properly
     * TODO: add correct processing here return something to user
     * TODO: return normal error that save did not passed fine
     * TODO: redirect user somewhere here to show him that an exception have happened
     * TODO: use the Spring exceptions and the good practice is using these
     * TODO: check the Spring article about custom handlers
     * TODO: https://www.baeldung.com/exception-handling-for-rest-with-spring
     * TODO: there will be normal REST handlers here not these saves
     */
    public void save(Periodical periodical) throws DBException {
        try {
            periodicalsRepository.save(periodical);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            logger.error("Cannot save periodical " + periodical.getPeriodicalId());
            logger.error(e.getMessage());
            throw new DBException("Cannot save periodical", e);
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
     * @param id the id
     * @return the optional periodical object
     *
     * TODO: null will never be returned here
     */
    public Optional<Periodical> get(Long id) {
        return periodicalsRepository.findById(id);
    }

    /**
     * Delete.
     *
     * @param id the periodical id
     */
    public void delete(Long id) {
        periodicalsRepository.deleteById(id);
    }
}