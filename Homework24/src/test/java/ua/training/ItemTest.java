package ua.training;

import ua.training.model.Item;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {
    
    @Test
    public void testItemIfEqual() {
        Item item = new Item();
        item.setId(1L);
        item.setName("FBDL");
        
        Item anotherItem = new Item();
        anotherItem.setId(1L);
        anotherItem.setName("FBDL");
        
        assertEquals(item, anotherItem);
    }
    
    @Test
    public void testItemInASet() {
        Item a = new Item();
        a.setId(1L);
        a.setName("A");
        
        Item b = new Item();
        b.setId(1L);
        b.setName("A");
        
        HashSet<Item> mySet = new HashSet<>();
        mySet.add(a);
        mySet.add(b);
        
        assertTrue(mySet.size() == 1);
    }
    
}
