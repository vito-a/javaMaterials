package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Periodical;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodicalsRepository extends JpaRepository<Periodical, Long> {
    Optional<Periodical> findByName(String name);
    Optional<Periodical> getPeriodicalByPeriodicalId(Long periodicalId);
    @Query("SELECT p FROM Periodical p WHERE p.name LIKE %?1%"
            + " OR p.description LIKE %?1%"
            + " OR p.categoryId LIKE %?1%"
            + " OR CONCAT(p.price, '') LIKE %?1%")
    List<Periodical> search(String keyword);
}
