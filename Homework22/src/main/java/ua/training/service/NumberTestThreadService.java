package ua.training.service;

/**
 * The Number test thread service.
 */
public class NumberTestThreadService implements Runnable {
    long threadNum;
    long max;
    long end;

    public NumberTestThreadService(long threadNum, long max, long end) {
        this.threadNum = threadNum;
        this.max = max;
        this.end = end;
    }

    @Override
    public synchronized void run() {
        long fetchStartTime = System.currentTimeMillis();
        System.out.println("Thread" + threadNum + ": start");
        for (long i = 0; i < max; i++) {
            if (System.currentTimeMillis() >= end) {
                break;
            }
            if ((threadNum % 2 == 0) && (i % 2 == 0) || (threadNum % 2 == 1) && (i % 2 == 1)) {
                System.out.println("Thread" + threadNum + ": " + i);
            }
        }
        System.out.println("It took " + (fetchStartTime - System.currentTimeMillis()) / 1000 + " seconds to run " + "Thread" + threadNum);
    }
}

