package ua.training.model.service;

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
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Periodical> getAllPeriodicals(int offset, int recordsOnPage,
                                              Sorting sorting, SortingType sortingType) {
        try (PeriodicalDao dao = daoFactory.createPeriodicalDao()) {
            return dao.getAllPeriodicals(offset, recordsOnPage, sorting, sortingType);
        }
    }

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
}