package ua.training;

import ua.training.model.Consumer;
import ua.training.model.Producer;
import ua.training.model.Store;
import ua.training.service.SequentalNumberThreadService;
import ua.training.service.SimpleNumberThreadService;

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
        /*
        ArrayList<Thread> threadsList = new ArrayList<Thread>();
        long end = System.currentTimeMillis() + timeout;
        for(long i = 0; i < threads; i++) {
            threadsList.add(new Thread(new SimpleNumberThreadService(i, maxNumber, end)));
        }

        for (Thread x : threadsList) {
            x.start();
        }
         */

        // Part 2 - Sequental concurrent odd and even numbers increasing thread service
        /*
        System.out.println("\n\nSequental concurrent odd and even numbers increasing thread service:\n");
        long fetchStartTime = System.currentTimeMillis();
        end = fetchStartTime + timeout;
        new SequentalNumberThreadService(threads, maxNumber, end);
         */

        // Part 3 - Producer - Consumer
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
