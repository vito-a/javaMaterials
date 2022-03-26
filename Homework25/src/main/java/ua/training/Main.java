package ua.training;

import ua.training.model.Item;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Task: The program demonstrates the work of HashMap: enters a set of name pairs (number and string, index and name),
 * from the keyboard, puts them into a HashMap and displays the contents of the HashMap.
 * An empty line is the end of data entry. Numbers can be repeated. Strings are always unique. The entered data must not be lost!
 * The program then prints the contents of the HashMap to the screen.
 * Print the 3rd and 4th maximum numbers.
 *    Explanation:
 *    The biggest number is the 1st maximum.
 *    The next maximum after it is the 2nd maximum
 *    Example:
 *    1 6 5 7 1 15 63 88
 *    First maximum - 88
 *    Second maximum - 63
 *    Third maximum - 15
 *    Fourth maximum - 7
 */

public class Main {

    public static void main(String[] args) {
        String indexString = "test";
        String name;
        Map<String, Integer> testMap = new HashMap<>();

        while (!indexString.isEmpty()) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please enter an Index Integer");
            indexString = keyboard.nextLine();
            if (indexString.isEmpty()) {
                break;
            }
            Integer index = Integer.parseInt(indexString);

            System.out.println("Please enter a Name String");
            name = keyboard.next();

            testMap.put(name, index);
        }

        for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
            System.out.println("Item from the map: ID=" + entry.getValue() + ", Name=" + entry.getKey());
        }
        
        LinkedHashMap<String, Integer> sortedMap = testMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> null, () -> new LinkedHashMap<String, Integer>()));

        System.out.println("\n\n");
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println("Item from sorted map: ID=" + entry.getValue() + ", Name=" + entry.getKey());
        }

        Map.Entry<String, Integer>[] entryArray = null;
        // 1. The iteration way
        /*
        int i = 1;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            // entryArray[i] = entry;
        }
         */
        // 2. The entrySet and Array way
        // Convert entrySet to Array using toArray method
        // System.out.println("\n\n");
        entryArray = sortedMap.entrySet().toArray(new Map.Entry[sortedMap.entrySet().size()]);

        System.out.println("3rd maximum key=" + entryArray[2].getValue());
        System.out.println("4th maximum key=" + entryArray[3].getValue());
    }
}
