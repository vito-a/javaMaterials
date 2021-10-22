package ua.testing.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.testing.periodicals.model.entity.Category;
import ua.testing.periodicals.repository.CategoriesRepository;

import java.util.List;
import java.util.Optional;

/**
 * The Categories service.
 */
@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepo;

    /**
     * List all list.
     *
     * @param keyword the keyword
     * @return the categories list
     */
    public List<Category> listAll(String keyword) {
        Optional<String> optionalKeyword = Optional.ofNullable(keyword);
        if (optionalKeyword.isPresent() && !optionalKeyword.equals(Optional.empty())) {
            return categoriesRepo.search(optionalKeyword.get());
        }
        return categoriesRepo.findAll(Sort.by(Sort.Direction.ASC, "catId"));
    }
}