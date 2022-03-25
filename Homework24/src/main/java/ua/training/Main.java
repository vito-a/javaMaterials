package ua.training;

import ua.training.model.Item;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Item> testSet = new HashSet<>();

        Item item1 = new Item();
        item1.setId(1L);
        item1.setName("Name1");
        System.out.println(item1.getClass());

        Item item2 = new Item();
        item2.setId(1L);
        item2.setName("Name1");

        // Checking the Case 2: two Items with the same values are equal
        // so in a HashSet only the first one inserted should exist
        testSet.add(item1);
        testSet.add(item2);

        for (Item i : testSet) {
            System.out.println("Item from set: ID=" + i.getId() + ", Name=" + i.getName());
        }
    }
}
