package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.PeriodicalDao;
import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.Periodical;

import java.util.List;

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
}