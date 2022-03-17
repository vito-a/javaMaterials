package ua.training;

import ua.training.model.Consumer;
import ua.training.model.Producer;
import ua.training.model.Store;
import ua.training.service.SequentalNumberMultiThreadService;
import ua.training.service.SimpleNumberThreadService;
import ua.training.service.SimpleSyncNumberMultiThreadService;
import ua.training.service.SimpleSyncNumberTwoThreadService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    class Control {
        public volatile boolean flag = false;
    }
    final Control control = new Control();

    // @Value("${ua.training.max:5000}")
    private static long maxNumber = 1000000;

    // @Value("${ua.training.timeout:15000}")
    private static long timeout = 30000;

    // @Value("${ua.training.threads:3}")
    private static long threads = 2;

    public static void main(String[] args) {
        long end;
        long fetchStartTime;
        final Map<String, String> properties = new HashMap<>();

        for (String arg: args) {
            String[] parts = arg.contains("=") ? arg.split("=") : (arg.contains(" ") ? arg.split(" ") : new String[]{arg, arg});
            String key = (parts[0].charAt(0) == '-') ? parts[0].replaceFirst("^[-]+", "") : parts[0];
            properties.put(key, parts[1]);
        }

        maxNumber = properties.containsKey("maxNumber") ? Integer.parseInt(properties.get("maxNumber")) : maxNumber;
        timeout = properties.containsKey("timeout") ? Integer.parseInt(properties.get("timeout")) : timeout;
        threads = properties.containsKey("threads") ? Integer.parseInt(properties.get("threads")) : threads;

        // Part 1 - Simple odd and even numbers increasing thread service
        System.out.println("\n\n1. Simple odd and even numbers increasing thread service:\n");
        ArrayList<Thread> threadsList = new ArrayList<Thread>();
        end = System.currentTimeMillis() + timeout;
        for(long i = 0; i < threads; i++) {
            threadsList.add(new Thread(new SimpleNumberThreadService(i, maxNumber, end)));
        }

        for (Thread x : threadsList) {
            x.start();
        }

        // Part 2 - Simple odd and even numbers increasing thread service with two threads
        /*
        System.out.println("\n\n2. Simple synchronization tools increasing thread service with two threads:\n");
        SimpleSyncNumberTwoThreadService service = new SimpleSyncNumberTwoThreadService(maxNumber);
        Thread t1 = new Thread(new Runnable() { public void run() { service.printEvenNumber(); } });
        Thread t2 = new Thread(new Runnable() { public void run() { service.printOddNumber(); } });
        t1.start();
        t2.start();
         */

        // Part 3 - Simple synchronization tools sequental odd and even numbers increasing thread service with many threads
        System.out.println("\n\n3. Simple synchronization tools sequental odd and even numbers increasing thread service with many threads:\n");
        fetchStartTime = System.currentTimeMillis();
        end = fetchStartTime + timeout;
        new SimpleSyncNumberMultiThreadService(threads, maxNumber, end);

        // Part 4 - Complex sync tools sequental concurrent odd and even numbers increasing thread service with many threads
        /*
        System.out.println("\n\n4. Complex sync tools sequental concurrent odd and even numbers increasing thread service with many threads:\n");
        fetchStartTime = System.currentTimeMillis();
        end = fetchStartTime + timeout;
        new SequentalNumberMultiThreadService(threads, maxNumber, end);
         */

        // Part 5 - Producer - Consumer
        System.out.println("\n\n5. Producer - Consumer:\n");
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
