package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Periodical;

import java.util.Optional;

@Repository
public interface PeriodicalsRepository extends JpaRepository<Periodical, Long> {
    Periodical findByName(String name);
}
