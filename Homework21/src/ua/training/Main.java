package ua.training;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.*;

public class Main {
    // Streams Homework Task 1:
    // The method takes a list of names. Returns a string of the form "1. Ivan, 3. Peter ...", only with names on odd indices, respectively.
    public static String numberedList(List<String> names) {
        return names.stream().filter(x -> ((names.indexOf(x) + 1) % 2 != 0)).map(x -> (names.indexOf(x)+1 + ". " + x + " ")).collect(Collectors.joining(", "));
    }
    public static String numberedList2(List<String> names) {
        return IntStream.range(0, names.size()).boxed().filter(x -> (x+1) % 2 != 0).map(x -> x+1 + ". " + names.get(x)).collect(Collectors.joining(", "));
    }

    // Streams Homework Task 2:
    // The method map list of string to uppercase and then sort descending.
    public static String[] sortUpperObjectsDesc(List<Object> names) {
        return names.stream().map(x -> x.toString().toUpperCase()).sorted(Collections.reverseOrder()).toArray(String[]::new);
    }
    public static List<String> sortUpperObjectsDesc2(List<String> names) {
        return names.stream().map(String::toUpperCase).sorted(Collections.reverseOrder()).collect(Collectors.toList());
    }

    // Streams Homework Task 3
    // Given and collection = Arrays.asList("1, 2, 0", "4, 5"). From the collection get all the numbers listed, separated by commas from all the elements
    public static String splitAndRejoin(List<String> names) {
        return names.stream().map(s -> s.split(",")).flatMap(Stream::of).map(String::trim).collect(Collectors.joining(", "));
    }
    public static List<String> splitAndRejoin2(List<String> names, String separator) {
        return names.stream().map(s -> Arrays.asList(s.split(separator))).flatMap(s -> s.stream()).collect(Collectors.toList());
    }

    // Streams Homework Task 4 Using Stream.iterate, make an infinite stream of random numbers — not by calling Math.random
    // but by directly implementing a linear congruential generator. In such a generator, you start with x[0] = seed and then
    // produce x[n + 1] = 1 (a x[n] + c) % m, for appropriate values of a, c, and m. You should implement a method with
    // parameters a, c, m, and seed that yields a Stream<Long>. Try out a = 25214903917, c = 11, and m = 2^48.
    public static Stream<Long> pseudoRandomLCG(Long a, Long c, Long m, Long seed) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    // Streams Homework Task 5
    // Write a method public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) that alternates
    // elements from the stream first and second, stopping when one of them runs out of elements.
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> firstIterator = first.iterator();
        Iterator<T> seconditerator = second.iterator();
        Stream<T> resultStream = Stream.empty();
        while (firstIterator.hasNext() & seconditerator.hasNext()){
            resultStream = Stream.concat(resultStream, Stream.of(firstIterator.next(), seconditerator.next()));
        }
        return resultStream;
    }
    public static <T> Stream<T> zip2(Stream<T> first, Stream<T> second) {
        Iterator<T> i2 = second.iterator();
        return first.flatMap(f1 -> i2.hasNext() ? Stream.of(f1, i2.next()) : null).takeWhile(Objects::nonNull);
    }

    // Streams Homework Task 6 (Optional)
    // It should be possible to concurrently collect stream results in a single ArrayList, instead of merging multiple array lists, provided it
    // has been constructed with the stream's size, since concurrent set operations at disjoint positions are threadsafe. How can you achieve this?
    public static synchronized <T> List<List<T>> zipParallel(List<List<T>> listOfLists) {
        int size = listOfLists.get(0).size();
        List<List<T>> result = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            int finalI = i;
            result.add(listOfLists.parallelStream().map(list -> list.get(finalI)).takeWhile(Objects::nonNull).collect(Collectors.toList()));
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a1 = new int[]{1,3,5};
        int[] a2 = new int[]{1,2,4,6};

        // Streams Homework Task 1:
        System.out.println("(Task 1)  " + numberedList(Arrays.asList("Alice", "Bob", "John", "Peter", "Martin", "Sam")));
        System.out.println("(Task 1 - 2)  " + numberedList2(Arrays.asList("Alice", "Bob", "John", "Peter", "Martin", "Sam")) + "\r\n");

        // Streams Homework Task 2:
        // System.out.println(Arrays.stream(sortObjects(new String[] {"a1", "a4", "a3", "a2", "a6", "a5"})).collect(Collectors.joining(", ")));
        System.out.println("(Task 2)  " + String.join(", ", sortUpperObjectsDesc(List.of(new String[]{"a1", "a4", "a3", "a2", "a6", "a5"}))));
        System.out.println("(Task 2 - 2)  " + String.join(", ", sortUpperObjectsDesc2(Arrays.asList("a1", "a4", "a3", "a2", "a6", "a5"))) + "\r\n");

        // Streams Homework Task 3:
        System.out.println("(Task 3)  " + splitAndRejoin(Arrays.asList("1, 2, 0", "4, 5")));
        System.out.println("(Task 3 - 2)  " + splitAndRejoin2(Arrays.asList("1, 2, 0", "4, 5"), ", ").stream().collect(Collectors.joining(", ")) + "\r\n");

        // Streams Homework Task 4:
        System.out.println("(Task 4)  " + pseudoRandomLCG(25214903917L, 11L, 2^48L, 1L).limit(6).map(Object::toString).collect(Collectors.joining(",")) + "\r\n");

        // Streams Homework Task 5:
        Stream<String> first = Stream.of("A", "B", "C");
        Stream<String> second = Stream.of("Apple", "Banana", "Carrot", "Doughnut");
        System.out.println("(Task 5)  " + zip(first, second).map(Object::toString).collect(Collectors.joining(",")));
        first = Stream.of("A", "B", "C");
        second = Stream.of("Apple", "Banana", "Carrot", "Doughnut");
        System.out.println("(Task 5)  " + zip2(first, second).map(Object::toString).collect(Collectors.joining(",")) + "\r\n");

        // Streams Homework Task 6(Optional)
        List<String> third = Arrays.asList("A", "B", "C");
        List<String> fourth = Arrays.asList("Apple", "Banana", "Carrot", "Doughnut");
        System.out.println("(Task 6)  " + zipParallel(List.of(third, fourth)).stream().map(Object::toString).collect(Collectors.joining(",")) + "\r\n");
    }
}
