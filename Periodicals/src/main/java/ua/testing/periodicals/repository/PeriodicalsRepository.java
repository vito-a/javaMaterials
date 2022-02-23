package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Periodical;

import java.util.List;
import java.util.Optional;

/**
 * The Periodicals repository.
 */
@Repository
public interface PeriodicalsRepository extends JpaRepository<Periodical, Long> {
    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the Optional periodical object
     */
    Optional<Periodical> findByName(String name);

    /**
     * Gets periodical by periodical id.
     *
     * @param periodicalId the periodical id
     * @return the Optional periodical object
     */
    Optional<Periodical> getPeriodicalByPeriodicalId(Long periodicalId);

    /**
     * Search list.
     *
     * @param keyword the keyword
     * @return the periodicals list
     */
    @Query("SELECT p FROM Periodical p WHERE p.name LIKE %?1%"
            + " OR p.description LIKE %?1%"
            + " OR p.categoryId LIKE %?1%"
            + " OR CONCAT(p.price, '') LIKE %?1%")
    // Optional<List<Periodical>> search(String keyword);
    List<Periodical> search(String keyword);
}
