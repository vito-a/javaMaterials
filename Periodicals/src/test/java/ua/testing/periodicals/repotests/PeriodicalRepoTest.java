package ua.testing.periodicals.repotests;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.repository.PeriodicalsRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;

/**
 * The Periodical repo test.
 * TODO: Main function is Subscribe so it is logical to check the user subscription
 * TODO: second method: with exception
 * TODO: third method: without exception
 * TODO: database writes are generally the INTEGRATION tests not the unit tests
 * TODO: in-memory database for example H2
 * TODO: but there is a question - how to check the MySQL specific tests in H2?
 * TODO: so the MySQL database is started in a Docker container but not automatically but manually
 */
public class PeriodicalRepoTest {

    @Autowired
    private PeriodicalsRepository periodicalRepo;

    /**
     * Test publication dao.
     */
    @Test
    public void testPublicationDao() {
        Optional<Periodical> periodical1 = periodicalRepo.getPeriodicalByPeriodicalId(1L);
        Optional<Periodical> periodical2 = periodicalRepo.findByName("Janes");
        List<Periodical> periodicals1 = periodicalRepo.findAll(Sort.by(Sort.Direction.ASC, "PeriodicalId"));
        List<Periodical> periodicals2 = null;

        try {
            periodicals2 = periodicalRepo.search("liberal");
            Assert.assertEquals(periodicals2.get(0).getPeriodicalId(), 1, 0);
            Assert.assertEquals(periodicals2.get(0).getName(), "Guardian");
        } catch (IndexOutOfBoundsException ex){

        }

        Assert.assertEquals(periodical1.orElse(new Periodical()).getName(), "Guardian");
        Assert.assertEquals(periodical2.orElse(new Periodical()).getName(), "Janes");
        Assert.assertEquals(periodical1.orElse(new Periodical()).getPeriodicalId(), 1L, 0);
        Assert.assertEquals(periodicals1.get(0).getPeriodicalId(), 1L, 0);
        assertNotNull(periodicals1);
    }
}
