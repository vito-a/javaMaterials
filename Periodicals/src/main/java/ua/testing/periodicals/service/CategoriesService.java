package ua.testing.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.testing.periodicals.model.entity.Category;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.repository.CategoriesRepository;
import ua.testing.periodicals.repository.PeriodicalsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepo;

    public List<Category> listAll(Optional<String> keyword) {
        if (keyword.isPresent()) {
            return categoriesRepo.search(keyword.get());
        }
        return categoriesRepo.findAll(Sort.by(Sort.Direction.ASC, "catId"));
    }
}