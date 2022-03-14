package ua.training;

import ua.training.service.NumberTestThreadService;

import java.util.ArrayList;

public class Main {

    // @Value("${ua.training.max:5000}")
    private static long max = 1000000;

    // @Value("${ua.training.timeout:15000}")
    private static long timeout = 30000;

    // @Value("${ua.training.threads:3}")
    private static long threads = 4;

    public static void main(String[] args) {
        ArrayList<Thread> threadsList = new ArrayList<Thread>();

        long end = System.currentTimeMillis() + timeout;
        for(long i = 0; i < threads; i++) {
            threadsList.add(new Thread(new NumberTestThreadService(i, max, end)));
        }

        for (Thread x : threadsList) {
            x.start();
        }
    }
}
