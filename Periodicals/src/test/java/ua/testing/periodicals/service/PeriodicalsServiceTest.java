package ua.testing.periodicals.service;

import junit.framework.TestCase;
import org.jooq.tools.jdbc.*;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ua.testing.periodicals.model.dao.DBException;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.repository.PeriodicalsRepository;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
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

        List<Periodical> result = null;
        try {
            when(mock.listAll("latest")).thenReturn(testList);
            result = mock.listAll("latest");
        } catch (DBException e) {
            e.printStackTrace();
        }

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
        List<Periodical> testList = null;
        try {
            testList = periodicalsService.listAll(null);
        } catch (DBException e) {
            e.printStackTrace();
        }
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

    static class JooqMockFileDatabaseMock {
        public boolean periodicalInsertMock (String path, String insertQuery) {
            int insertedRowCount = 0;
            MockDataProvider db;
            try {
                db = new MockFileDatabase(new File(getClass().getResource(path).toURI()));
                Connection c = new MockConnection(db);
                Statement s = c.createStatement();
                insertedRowCount = s.executeUpdate(insertQuery);
            } catch (IOException | SQLException | URISyntaxException e) {
                e.printStackTrace();
            }

            return (insertedRowCount == 1);
        }

        public int periodicalSelectMock (String path, String selectQuery) {
            int selectCount = 0;
            MockDataProvider db;
            try {
                db = new MockFileDatabase(new File(getClass().getResource(path).toURI()));
                Connection c = new MockConnection(db);
                Statement s = c.createStatement();

                try (PreparedStatement ps = c.prepareStatement(selectQuery)) {
                    ResultSet rs = ps.executeQuery();
                    System.out.println("Result:");
                    System.out.println("-------");
                    while (rs.next()) {
                        System.out.println(rs.getString(1) + " " + rs.getString(2)
                                + " " + rs.getString(3) + " " + rs.getString(4)
                                + " " + rs.getString(5));
                        selectCount++;
                    }
                }
            } catch (IOException | SQLException | URISyntaxException e) {
                e.printStackTrace();
            }

            return selectCount;
        }
    }

    public void testSave() {
        final Boolean[] resultInsert = new Boolean[1];
        JooqMockFileDatabaseMock mock = new JooqMockFileDatabaseMock();

        Periodical periodical1 = new Periodical();
        periodical1.setPeriodicalId(7L);
        periodical1.setName("Test");
        periodical1.setPrice(800L);
        periodical1.setDescription("Test description 1");
        periodical1.setCategoryId(4L);

        PeriodicalsService mock2 = org.mockito.Mockito.mock(PeriodicalsService.class);

        try {
            String insertPath = "/mockdb/periodicalServiceTestSaveInsert.txt";
            String insertQuery = "INSERT INTO `periodicalsdb`.`periodicals` (`periodical_id`, `name`, `description`, `cat_id`, `price`) VALUES ('7', 'Test', 'Test description 1', '4', '800.0')";
            Mockito.doAnswer(new Answer<Void>() {
                public Void answer(InvocationOnMock invocation) {
                    resultInsert[0] = mock.periodicalInsertMock(insertPath, insertQuery);
                    return null;
                }
            }).when(mock2).save(periodical1);
            mock2.save(periodical1);
        } catch (DBException e) {
            e.printStackTrace();
        }

        assertTrue(resultInsert[0]);

        String selectPath = "/mockdb/periodicalServiceTestSaveSelect.txt";
        // String selectQuery = "SELECT periodical_id, name, description, cat_id, price FROM periodicals WHERE periodical_id = '7' AND name = 'Test' AND description = 'Test description 1' AND cat_id = '4' AND price = '800.0';";
        // String selectQuery = "SELECT periodical_id, name, description, cat_id, price FROM periodicals WHERE periodical_id = ? AND name = ? AND description = ? AND cat_id = ? AND price = ?;";
        String selectQuery = "SELECT periodical_id, name, description, cat_id, price FROM periodicals";

        assertEquals(mock.periodicalSelectMock(selectPath, selectQuery), 7);
    }

    public void testSubscribe() {
        final Boolean[] resultInsert = new Boolean[1];
        JooqMockFileDatabaseMock mock = new JooqMockFileDatabaseMock();

        PeriodicalsService mock2 = org.mockito.Mockito.mock(PeriodicalsService.class);

        try {
            String insertPath = "/mockdb/periodicalServiceTestSubscribeInsert.txt";
            String insertQuery = "INSERT INTO `periodicalsdb`.`periodicals` (`periodical_id`, `name`, `description`, `cat_id`, `price`) VALUES ('7', 'Test', 'Test description 1', '4', '800.0')";
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = LocalDate.now().plusYears(1);

            Mockito.doAnswer(new Answer<Void>() {
                public Void answer(InvocationOnMock invocation) {
                    resultInsert[0] = mock.periodicalInsertMock(insertPath, insertQuery);
                    return null;
                }
            }).when(mock2).subscribe(1L, 1L, startDate, endDate);
            mock2.subscribe(1L, 1L, startDate, endDate);
        } catch (DBException e) {
            e.printStackTrace();
        }

        assertTrue(resultInsert[0]);

        String selectPath = "/mockdb/periodicalServiceTestSubscribeSelect.txt";
        // String selectQuery = "SELECT periodical_id, name, description, cat_id, price FROM periodicals WHERE periodical_id = '7' AND name = 'Test' AND description = 'Test description 1' AND cat_id = '4' AND price = '800.0';";
        // String selectQuery = "SELECT periodical_id, name, description, cat_id, price FROM periodicals WHERE periodical_id = ? AND name = ? AND description = ? AND cat_id = ? AND price = ?;";
        String selectQuery = "SELECT periodical_id, name, description, cat_id, price FROM periodicals";

        assertEquals(mock.periodicalSelectMock(selectPath, selectQuery), 7);
    }

    public void testGet() {
        JooqMockFileDatabaseMock mock = new JooqMockFileDatabaseMock();

        Periodical periodical1 = new Periodical();
        periodical1.setPeriodicalId(1L);
        periodical1.setName("Guardian");
        periodical1.setDescription("Latest news, sport, business, comment, analysis and reviews from the world's leading liberal voice");
        periodical1.setCategoryId(1L);
        periodical1.setPrice(200L);

        String selectPath = "/mockdb/periodicalServiceTestGetSelect.txt";
        String selectQuery = "SELECT periodical_id, name, description, cat_id, price FROM periodicals";

        assertEquals(mock.periodicalSelectMock(selectPath, selectQuery), 1);
    }

    public void testDelete() {
    }
}