package ua.testing.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.repository.PeriodicalsRepository;

import java.util.List;

@Service
public class PeriodicalsService {
    @Autowired
    private PeriodicalsRepository periodicalsRepo;

    @Value("${periodicals.pageSize:5}")
    private int pageSize;

    public List<Periodical> listAll(String keyword) {
        if (keyword != null) {
            return periodicalsRepo.search(keyword);
        }
        return periodicalsRepo.findAll();
    }

    public Page<Periodical> listAll(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );

        return periodicalsRepo.findAll(pageable);
    }

    public void save(Periodical periodical) {
        periodicalsRepo.save(periodical);
    }

    public Periodical get(Long id) {
        return periodicalsRepo.findById(id).get();
    }

    public void delete(Long id) {
        periodicalsRepo.deleteById(id);
    }
}