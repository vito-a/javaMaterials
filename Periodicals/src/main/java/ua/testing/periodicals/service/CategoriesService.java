package ua.testing.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.testing.periodicals.model.entity.Category;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.repository.CategoriesRepository;
import ua.testing.periodicals.repository.PeriodicalsRepository;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepo;

    // TODO: Optional
    public List<Category> listAll(String keyword) {
        if (keyword != null) {
            return categoriesRepo.search(keyword);
        }
        return categoriesRepo.findAll(Sort.by(Sort.Direction.ASC, "catId"));
    }
}