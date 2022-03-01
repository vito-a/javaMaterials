package ua.testing.periodicals.service;

import junit.framework.TestCase;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.repository.PeriodicalsRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class PeriodicalsServiceTest extends TestCase {

    final PeriodicalsService periodicalsService = new PeriodicalsService();
    private PeriodicalsRepository periodicalsRepository;

    private static final Logger logger = LoggerFactory.getLogger(PeriodicalsServiceTest.class);

    public void testListAllNonNull() {
        List<Periodical> testList = new ArrayList<Periodical>();

        // Periodical periodical1 = mock(Periodical.class);
        Periodical periodical1 = new Periodical();
        periodical1.setPeriodicalId(1L);
        periodical1.setName("Guardian");
        periodical1.setPrice(200L);
        periodical1.setDescription("Latest news, sport, business, comment, analysis and reviews from the world's leading liberal voice");
        periodical1.setCategoryId(1L);
        testList.add(periodical1);

        // Periodical periodical2 = mock(Periodical.class);
        Periodical periodical2 = new Periodical();
        periodical2.setPeriodicalId(5L);
        periodical2.setName("Janes");
        periodical2.setPrice(600L);
        periodical2.setDescription("The latest defence and security news - the trusted source for defence intelligence");
        periodical2.setCategoryId(5L);
        testList.add(periodical2);

        PeriodicalsService mock = org.mockito.Mockito.mock(PeriodicalsService.class);

        when(mock.listAll("latest")).thenReturn(testList);

        List<Periodical> result = mock.listAll("latest");

        assertNotNull(result);
        assertEquals(result.size(), 2);

        assertEquals(periodical1.getPeriodicalId(), 1L, 0);
        assertEquals(periodical1.getName(), "Guardian");
        assertEquals(periodical1.getPrice(), 200L,0);
        assertEquals(periodical1.getDescription(), "Latest news, sport, business, comment, analysis and reviews from the world's leading liberal voice");
        assertEquals(periodical1.getCategoryId(), 1L, 0);
        assertEquals(periodical1.toString(),"Periodical{id=1, name='Guardian', description='Latest news, sport, business, comment, analysis and reviews from the world's leading liberal voice', catId='1', price='200'}");
        assertEquals(periodical2.toString(),"Periodical{id=5, name='Janes', description='The latest defence and security news - the trusted source for defence intelligence', catId='5', price='600'}");
    }

    public void testListAllNull() {
        List<Periodical> testList = periodicalsService.listAll(null);
        assertNull(testList);
    }

    public void testTestListAll() {
        List<Periodical> testList = new ArrayList<Periodical>();

        Periodical periodical1 = new Periodical();
        periodical1.setPeriodicalId(1L);
        periodical1.setName("Guardian");
        periodical1.setPrice(200L);
        periodical1.setDescription("Latest news, sport, business, comment, analysis and reviews from the world's leading liberal voice");
        periodical1.setCategoryId(1L);
        testList.add(periodical1);

        Periodical periodical2 = new Periodical();
        periodical2.setPeriodicalId(2L);
        periodical2.setName("New York Times");
        periodical2.setPrice(300L);
        periodical2.setDescription("Live news, investigations, opinion, photos and video by the journalists from more than 150 countries around the world.");
        periodical2.setCategoryId(1L);
        testList.add(periodical2);

        Periodical periodical3 = new Periodical();
        periodical3.setPeriodicalId(3L);
        periodical3.setName("Wall Street Journal");
        periodical3.setPrice(400L);
        periodical3.setDescription("Breaking news and analysis from the U.S. and around the world at WSJ.com.");
        periodical3.setCategoryId(2L);
        testList.add(periodical3);

        Periodical periodical4 = new Periodical();
        periodical4.setPeriodicalId(4L);
        periodical4.setName("Lancet");
        periodical4.setPrice(500L);
        periodical4.setDescription("Regional Health - Europe publishes a Series of eleven papers by experts from different areas of public health");
        periodical4.setCategoryId(2L);
        testList.add(periodical4);

        Periodical periodical5 = new Periodical();
        periodical5.setPeriodicalId(5L);
        periodical5.setName("Janes");
        periodical5.setPrice(600L);
        periodical5.setDescription("The latest defence and security news - the trusted source for defence intelligence");
        periodical5.setCategoryId(4L);
        testList.add(periodical5);

        PeriodicalsService mock = org.mockito.Mockito.mock(PeriodicalsService.class);
        Page<Periodical> pagedResponse = new PageImpl(testList);
        when(mock.listAll(1, "PeriodicalId", "asc")).thenReturn((pagedResponse));

        Page<Periodical> result = mock.listAll(1, "PeriodicalId", "asc");

        assertNotNull(result);
        assertEquals(result.getTotalElements(), 5);
        assertEquals(result.getTotalPages(), 1);
    }

    public class Mocking {
        public Mocking () throws SQLException {
            MockDataProvider db;
            try {
                db = new MockFileDatabase(
                        Mocking.class.getResourceAsStream("/mocking.txt"));
                Connection c = new MockConnection(db);
                Statement s = c.createStatement();
                    System.out.println("Actors:");
                    System.out.println("-------");
                    try (ResultSet rs = s.executeQuery(
                            "select first_name, last_name from actor")) {
                        while (rs.next())
                            System.out.println(rs.getString(1)
                                    + " " + rs.getString(2));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void testSave() {
    }

    public void testSubscribe() {
    }

    public void testGet() {
    }

    public void testTestGet() {
    }

    public void testDelete() {
    }
}