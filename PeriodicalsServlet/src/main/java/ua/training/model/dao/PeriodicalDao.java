package ua.training.model.dao;

import ua.training.model.dao.util.Sorting;
import ua.training.model.dao.util.SortingType;
import ua.training.model.entity.Periodical;

import java.util.List;

public interface PeriodicalDao extends GenericDao<Periodical> {
    List<Periodical> getAllPeriodicals(int offset, int recordsOnPage,
                                       Sorting sorting, SortingType sortingType);
    int getPeriodicalsCount(long catId);

    List<Periodical> search(String s);

    int subscribe(int periodicalId, long userId);

    List<Periodical> categoryPeriodicals(long catId);
}
