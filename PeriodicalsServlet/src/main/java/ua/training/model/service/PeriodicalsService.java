package ua.training.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.PeriodicalDao;
import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.Periodical;

import java.util.List;
import java.util.Optional;

/**
 * The Periodicals service.
 */
public class PeriodicalsService {
    /**
     * The Dao factory.
     */
    DaoFactory daoFactory = DaoFactory.getInstance();

    private final Logger logger = LogManager.getLogger(PeriodicalsService.class.getName());

    /**
     * Find periodical by id.
     *
     * @param id the id
     * @return the periodical
     */
    public static Periodical findById(int id) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (PeriodicalDao dao = daoFactory.createPeriodicalDao()) {
            return dao.findById(id);
        }
    }

    /**
     * Delete periodical.
     *
     * @param id the id
     */
    public static void delete(int id) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (PeriodicalDao dao = daoFactory.createPeriodicalDao()) {
            dao.delete(id);
        }
    }

    /**
     * Subscribe to periodical.
     *
     * @param periodicalId the periodical id
     * @param userId       the user id
     */
    public static void subscribe(int periodicalId, long userId) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (PeriodicalDao dao = daoFactory.createPeriodicalDao()) {
            dao.subscribe(periodicalId, userId);
        }
    }

    /**
     * Gets all periodicals.
     *
     * @param offset        the offset
     * @param recordsOnPage the records on page
     * @param sorting       the sorting
     * @param sortingType   the sorting type
     * @return the all periodicals
     */
    public List<Periodical> getAllPeriodicals(int offset, int recordsOnPage,
                                              Sorting sorting, SortingType sortingType) {
        try (PeriodicalDao dao = daoFactory.createPeriodicalDao()) {
            return dao.getAllPeriodicals(offset, recordsOnPage, sorting, sortingType);
        }
    }

    /**
     * Gets periodicals count.
     *
     * @param catId the cat id
     * @return the periodicals count
     */
    public int getPeriodicalsCount(long catId) {
        try (PeriodicalDao dao = daoFactory.createPeriodicalDao()) {
            return dao.getPeriodicalsCount(catId);
        }
    }

    /**
     * Search periodicals.
     *
     * @param keyword the search keyword
     * @return the periodicals list
     */
    public List<Periodical> searchPeriodicals(String keyword) {
        Optional<String> optionalKeyword = Optional.ofNullable(keyword);
        try (PeriodicalDao dao = daoFactory.createPeriodicalDao()) {
            if (optionalKeyword.isPresent()) {
                return dao.search(optionalKeyword.get());
            }
            return dao.findAll();
        }
    }

    /**
     * Create periodical.
     *
     * @param periodical the periodical
     * @return the int
     */
    public int create(Periodical periodical) {
        int result = 0;
        try (PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao()) {
            result = periodicalDao.create(periodical);
            logger.info("Attempted to create periodical with params (name, description) : " +
                    "(" + periodical.getName() + "," + periodical.getDescription() + ")");
        }

        return result;
    }

    /**
     * Update periodical.
     *
     * @param periodical the periodical
     * @return the int
     */
    public int update(Periodical periodical) {
        int result = 0;
        try (PeriodicalDao periodicalDao = daoFactory.updatePeriodicalDao()) {
            result = periodicalDao.update(periodical);
            logger.info("Attempted to update periodical with params (name, description) : " +
                    "(" + periodical.getName() + "," + periodical.getDescription() + ")");
        }

        return result;
    }

    /**
     * Category periodicals list.
     *
     * @param catId the cat id
     * @return the list
     */
    public List<Periodical> categoryPeriodicals(long catId) {
        try (PeriodicalDao dao = daoFactory.createPeriodicalDao()) {
            return dao.categoryPeriodicals(catId);
        }
    }
}