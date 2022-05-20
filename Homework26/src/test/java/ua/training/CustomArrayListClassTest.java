package ua.training;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.training.model.CustomArrayListClass;

import java.util.Arrays;
import java.util.UUID;

public class CustomArrayListClassTest {

    private CustomArrayListClass customArrayList;

    @BeforeEach
    public void init() {
        customArrayList = new CustomArrayListClass();
    }

    @Test
    public void testNonNull() {
        Assertions.assertNotNull(customArrayList);
    }

    @Test
    public void testNullTest() {
        Assertions.assertTrue(customArrayList.getSize() == 0);
        Assertions.assertTrue(customArrayList.isEmpty());
        Assertions.assertFalse(customArrayList.contains("Peace"));
    }

    @Test
    public void testAdd() {
        Object number = 12;
        customArrayList.add(number);
        Assertions.assertTrue(customArrayList.get(0).equals(number));
        Assertions.assertTrue(customArrayList.contains(number));
    }

    @Test
    public void testAddByIndex() {
        int loopTimes = (int) (Math.random() * 100);
        for (int i = 0; i < loopTimes; i++) {
            customArrayList.add(i, String.valueOf(i));
        }

        Assertions.assertTrue(customArrayList.getSize() == loopTimes);
    }

    @Test
    public void testGet() {
        Assertions.assertTrue(customArrayList.getSize() == 0);
        int loopTimes = 20;
        String prefix = "item-";
        for (int i = 0; i < loopTimes; i++) {
            customArrayList.add(prefix + i);
        }

        for (int i = 0; i < loopTimes; i++) {
            Assertions.assertTrue(customArrayList.get(i).equals(prefix + i));
        }
    }

    @Test
    public void testSize() {
        int loopTimes = (int) (Math.random() * 100);
        for (int i = 0; i < loopTimes; i++) {
            customArrayList.add("Apple-" + i);
        }

        Assertions.assertTrue(customArrayList.getSize() == loopTimes);
    }

    @Test
    public void testDestroy() {
        int loopTimes = (int) (Math.random() * 100);
        for (int i = 0; i < loopTimes; i++) {
            customArrayList.add(String.valueOf(i));
        }

        Assertions.assertTrue(customArrayList.getSize() == loopTimes);
        Assertions.assertFalse(customArrayList.isEmpty());

        customArrayList.clear();
        Assertions.assertTrue(customArrayList.getSize() == 0);
        Assertions.assertTrue(customArrayList.isEmpty());
    }

    @Test
    public void testRemove() {
        int loopTimes = (int) (Math.random() * 100);
        for (int i = 0; i < loopTimes; i++) {
            customArrayList.add(String.valueOf(i));
        }

        Assertions.assertTrue(customArrayList.getSize() == loopTimes);

        for (int i = 0; i < loopTimes; i++) {
            customArrayList.remove(customArrayList.get(0));
        }

        Assertions.assertTrue(customArrayList.getSize() == 0);
        Assertions.assertTrue(customArrayList.isEmpty());
    }

    @Test
    public void testSet() {
        int loopTimes = (int) (Math.random() * 100);

        for (int i = 0; i < loopTimes; i++) {
            customArrayList.add(0, String.valueOf(i));
        }
        Assertions.assertFalse(customArrayList.getSize() == 0);
        Assertions.assertFalse(customArrayList.isEmpty());

        String randomString = UUID.randomUUID().toString();

        int randomIndex = (int) (Math.random() * customArrayList.getSize());
        customArrayList.set(randomIndex, randomString);
        Assertions.assertTrue(randomString.equals(customArrayList.get(randomIndex)));

    }

    @Test
    public void testIndexOf() {
        int loopTimes = (int) (Math.random() * 100);

        for (int i = 0; i < loopTimes; i++) {
            customArrayList.add(0, String.valueOf(i));
        }

        int randomIndex = (int) (Math.random() * customArrayList.getSize());
        Object target = customArrayList.get(randomIndex);

        for (int i = 0; i < loopTimes; i++) {
            Assertions.assertTrue(Integer.valueOf(randomIndex).equals(customArrayList.indexOf(target)));
        }

        System.out.println(customArrayList.indexOf(null));
    }

    @Test
    public void testToArray() {
        int loopTimes = (int) (Math.random() * 100);
        for (int i = 0; i < loopTimes; i++) {
            customArrayList.add(String.valueOf(i));
        }

        System.out.println("loopTimes:" + loopTimes);
        System.out.println(Arrays.toString(customArrayList.toArray()));
    }

    @Test
    public void testIterator() {
        int loopTimes = (int) (Math.random() * 100);
        for (int i = 0; i < loopTimes; i++) {
            customArrayList.add(String.valueOf(i));
        }

        System.out.println("testIterator loopTimes:" + loopTimes);
        for (Object obj : customArrayList) {
            System.out.print(obj + "\t");
        }

    }
}