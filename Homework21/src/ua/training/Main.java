package ua.training;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.*;

public class Main {
    // Streams Homework Task 1:
    // The method takes a list of names. Returns a string of the form "1. Ivan, 3. Peter ...", only with names on odd indices, respectively.
    public static String numberedList(List<String> names) {
        return names.stream().filter(x -> (names.indexOf(x) % 2 != 0)).map(x -> (names.indexOf(x) + ". " + x + " ")).collect(Collectors.joining(", "));
    }

    // Streams Homework Task 2:
    //The method map list of string to uppercase and then sort descending.
    public static String[] sortObjectsDesc(Object[] names) {
        return Arrays.stream(names).sorted(Collections.reverseOrder()).toArray(String[]::new);
    }

    // Streams Homework Task 3
    // Given and collection = Arrays.asList("1, 2, 0", "4, 5"). From the collection get all the numbers listed, separated by commas from all the elements
    public static String splitAndRejoin(List<String> names) {
        return names.stream().map(s -> s.split(",")).flatMap(Stream::of).map(String::trim).collect(Collectors.joining(","));
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
        Iterator<T> i2 = second.iterator();
        return first.flatMap(f1 -> i2.hasNext() ? Stream.of(f1, i2.next()) : null).takeWhile(Objects::nonNull);
    }

    // Streams Homework Task 6(Optional)
    // It should be possible to concurrently collect stream results in a single ArrayList, instead of merging multiple array lists, provided it
    // has been constructed with the stream's size, since concurrent set operations at disjoint positions are threadsafe. How can you achieve this?
    public static <T> Stream<T> zipParallel(Stream<T> first, Stream<T> second, BiFunction<T, T, T> zipper) {
        final Iterator<T> i1 = first.iterator();
        final Iterator<T> i2 = second.iterator();
        final Iterator<T> i3 = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return i1.hasNext() && i2.hasNext();
            }

            @Override
            public T next() {
                return zipper.apply(i1.next(), i2.next());
            }
        };
        final boolean parallel = first.isParallel() || second.isParallel();
        return iteratorToFiniteStream(i3, parallel);
    }

    public static <T> Stream<T> iteratorToFiniteStream(Iterator<T> iterator, boolean parallel) {
        final Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }

    public static<T> Stream<T> zipParallel2(Stream<? extends T> a,
                                         Stream<? extends T> b,
                                         BiFunction<? super T, ? super T, ? extends T> zipper) {
        Objects.requireNonNull(zipper);
        Spliterator<? extends T> aSpliterator = Objects.requireNonNull(a).spliterator();
        Spliterator<? extends T> bSpliterator = Objects.requireNonNull(b).spliterator();

        // Zipping looses DISTINCT and SORTED characteristics
        int characteristics = aSpliterator.characteristics() & bSpliterator.characteristics() &
                ~(Spliterator.DISTINCT | Spliterator.SORTED);

        long zipSize = ((characteristics & Spliterator.SIZED) != 0)
                ? Math.min(aSpliterator.getExactSizeIfKnown(), bSpliterator.getExactSizeIfKnown())
                : -1;

        Iterator<T> aIterator = Spliterators.iterator(aSpliterator);
        Iterator<T> bIterator = Spliterators.iterator(bSpliterator);
        Iterator<T> cIterator = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return aIterator.hasNext() && bIterator.hasNext();
            }

            @Override
            public T next() {
                return zipper.apply(aIterator.next(), bIterator.next());
            }
        };

        Spliterator<T> split = Spliterators.spliterator(cIterator, zipSize, characteristics);
        return (a.isParallel() || b.isParallel())
                ? StreamSupport.stream(split, true)
                : StreamSupport.stream(split, false);
    }

    private static String zipper(String o1, String o2) {
        return o1 + "," + o2;
    }

    public static void main(String[] args) {
        int[] a1 = new int[]{1,3,5};
        int[] a2 = new int[]{1,2,4,6};

        // Streams Homework Task 1:
        System.out.println("(Task 1)  " + numberedList(Arrays.asList("Alice", "Bob", "John", "Peter", "Martin", "Sam")) + "\r\n");

        // Streams Homework Task 2:
        // System.out.println(Arrays.stream(sortObjects(new String[] {"a1", "a4", "a3", "a2", "a6", "a5"})).collect(Collectors.joining(", ")));
        System.out.println("(Task 2)  " + String.join(", ", sortObjectsDesc(new String[]{"a1", "a4", "a3", "a2", "a6", "a5"})) + "\r\n");

        // Streams Homework Task 3:
        System.out.println("(Task 3)  " + splitAndRejoin(Arrays.asList("1, 2, 0", "4, 5")) + "\r\n");

        // Streams Homework Task 4:
        System.out.println("(Task 4)  " + pseudoRandomLCG(25214903917L, 11L, 2^48L, 1L).limit(6).map(Object::toString).collect(Collectors.joining(",")) + "\r\n");

        // Streams Homework Task 5:
        Stream<String> first = Stream.of("A", "B", "C");
        Stream<String> second = Stream.of("Apple", "Banana", "Carrot", "Doughnut");
        System.out.println("(Task 5)  " + zip(first, second).map(Object::toString).collect(Collectors.joining(",")) + "\r\n");

        // Streams Homework Task 6(Optional)
        System.out.println("(Task 6)  " + zipParallel2(first.parallel(), second.parallel(), Main::zipper).map(Object::toString).collect(Collectors.joining(",")) + "\r\n");
    }
}
