package ua.training.model.dao;

import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.Periodical;

import java.util.List;

/**
 * The Periodical DAO interface.
 */
public interface PeriodicalDao extends GenericDao<Periodical> {
    /**
     * Gets all periodicals.
     *
     * @param offset        the offset
     * @param recordsOnPage the records on page
     * @param sorting       the sorting
     * @param sortingType   the sorting type
     * @return the all periodicals
     */
    List<Periodical> getAllPeriodicals(int offset, int recordsOnPage, Sorting sorting, SortingType sortingType);

    /**
     * Gets periodicals count.
     *
     * @param catId the cat id
     * @return the periodicals count
     */
    int getPeriodicalsCount(long catId);

    /**
     * Search periodicals.
     *
     * @param s the s
     * @return the list
     */
    List<Periodical> search(String s);

    /**
     * Subscribe to periodical.
     *
     * @param periodicalId the periodical id
     * @param userId       the user id
     * @return the int
     */
    int subscribe(int periodicalId, long userId);

    /**
     * List periodicals in a category.
     *
     * @param catId the cat id
     * @return the list
     */
    List<Periodical> categoryPeriodicals(long catId);
}
