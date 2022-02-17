package ua.testing.periodicals.entitytests;

import org.junit.Test;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.model.entity.Role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The Role tests.
 */
public class RoleTests {
    /**
     * Test role entity.
     */
    @Test
    public void testRoleEntity() {
        Role role = new Role();

        assertNotNull(role);

        role.setId(100);
        role.setName("MODERATOR");

        assertEquals(role.getId(), 100L, 0);
        assertEquals(role.getName(), "MODERATOR");
        assertEquals(role.toString(),"Role {Id=100, name='MODERATOR'}");
    }
}
