package ua.testing.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.repository.PeriodicalsRepository;

import java.util.List;

@Service
public class PeriodicalsService {
    @Autowired
    private PeriodicalsRepository periodicalsRepo;

    public List<Periodical> listAll(String keyword) {
        if (keyword != null) {
            return periodicalsRepo.search(keyword);
        }
        return periodicalsRepo.findAll();
    }
}