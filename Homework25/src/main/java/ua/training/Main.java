package ua.training;

import ua.training.model.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Task: The program demonstrates the work of HashMap: enters a set of name pairs (number and string, index and name),
 * from the keyboard, puts them into a HashMap and displays the contents of the HashMap.
 * An empty line is the end of data entry. Numbers can be repeated. Strings are always unique. The entered data must not be lost!
 * The program then prints the contents of the HashMap to the screen.
 * Print the 5th and 6th minimum numbers.
 *    Explanation:
 *    The smallest number is the 1st minimum.
 *    The next minimum after it is the 2nd minimum
 *    Example:
 *    1 6 5 7 1 15 63 88
 *    First minimum - 1
 *    Second minimum - 1
 *    Third minimum - 5
 *    Fourth minimum - 6
 *    Fifth minimum - 7
 *    Sixth minimum - 15
 */

public class Main {

    public static void main(String[] args) {
        String indexString = "test";
        String name;
        Map<Integer, String> testMap = new HashMap<>();

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

            testMap.put(index, name);
        }

        for (Map.Entry<Integer, String> i : testMap.entrySet()) {
            System.out.println("Item from set: ID=" + i.getKey() + ", Name=" + i.getValue());
        }
    }
}
