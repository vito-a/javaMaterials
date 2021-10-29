package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.PeriodicalDao;
import ua.training.model.entity.Category;
import ua.training.model.entity.Periodical;

import java.util.List;

/**
 * The Periodicals service.
 */
public class PeriodicalsService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Periodical> getAllPeriodicals(){
        try (PeriodicalDao dao = daoFactory.createPeriodicalDao()) {
            return dao.findAll();
        }
    }
}