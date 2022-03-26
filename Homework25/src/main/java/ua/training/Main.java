package ua.training;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        // main.Task1();
        main.Task2();
    }

    /**
     * Task1: The program demonstrates the work of HashMap: enters a set of name pairs (number and string, index and name),
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
    public void Task1 (){
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
            System.out.println("CustomMapInterface from the map: ID=" + entry.getValue() + ", Name=" + entry.getKey());
        }

        LinkedHashMap<String, Integer> sortedMap = testMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> null, () -> new LinkedHashMap<String, Integer>()));

        System.out.println("\n\n");
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println("CustomMapInterface from sorted map: ID=" + entry.getValue() + ", Name=" + entry.getKey());
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

    /**
     * Task2: In a Map<List<String>, List<String>> resultSet,
     * the resultSet contains {[key1]-[value1], [key2]-[value2], [key3]-[value3]}
     * Fetch the "value2" alone without using Iterator.
     * Fetch all the values without using Iterator.
     */
    public void Task2 () {
        Map<List<String>, List<String>> resultSet = new HashMap< List<String>, List<String>>();

        final List<String> keyList1 = new ArrayList<String>(){ { add("a"); add("b");}};
        final List<String> valueList1 = new ArrayList<String>(){ { add("1"); add("2");}};
        resultSet.put(keyList1, valueList1);

        final List<String> keyList2 = new ArrayList<String>(){ { add("A"); add("B");}};
        final List<String> valueList2 = new ArrayList<String>(){ { add("11"); add("12");}};
        resultSet.put(keyList2, valueList2);

        // keyList2.add("C");
        // System.out.println(keyList2);

        System.out.println(resultSet.get(keyList2));

        // List<String> equivalentKeyList = new LinkedList<String>(){ { add("A"); add("B"); add("C");}};
        System.out.println(resultSet.get(new LinkedList<String>(){ { add("A"); add("B"); }}));
    }

    /**
     * Task3: Implement a custom HashMap
     */
    public void Task3 () {

    }
}

