package ua.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>(
                Arrays.asList(4,5,-6,4,5,3,4,2,4,5,7));

        System.out.println(arrayList);

        SimpleArray<Integer> list = new SimpleArray<>();

        list.add(0);
        list.add(1);
        list.addAll(Arrays.asList(1,2,3));
        list.add(null);

        System.out.println(list);

        LinkedContainer<Long> longArray = new LinkedContainer<>(
                Arrays.asList(10L, 9L, 8L, 7L, 6L, 5L));

        LinkedContainer.sort(longArray);

        longArray.addFirst(30L);

        System.out.printf("size: %d first: %d last: %d\n",longArray.size(),longArray.getFirst(),longArray.getLast());

        System.out.println(longArray);

        longArray.addLast(100L);
        System.out.println(longArray);

        System.out.printf("longArray[%d] = %d\n", 4, longArray.get(4));

        // Out of bounds
        // longArray.get(20);
    }
}
