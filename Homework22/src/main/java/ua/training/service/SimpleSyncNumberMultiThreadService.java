package ua.training.service;

import java.util.ArrayList;

/**
 * The Simple Synchronization tools Number test thread service.
 */
public class SimpleSyncNumberMultiThreadService {
    long threadsNumber;
    long max;
    long end;
    long currentNum = 0;

    /**
     * Instantiates a new File test thread service.
     *
     * @param threadsNumber  the threads number
     * @param max            the max number
     * @param end            the end time
     */
    public SimpleSyncNumberMultiThreadService(long threadsNumber, long max, long end) {
        this.threadsNumber = threadsNumber;
        this.max = max;
        this.end = end;
        int remainder = 0;

        ArrayList<Thread> threadsList = new ArrayList<Thread>();

        for (int threadNum = 0; threadNum < threadsNumber; threadNum++) {
            final long threadID = threadNum;
            final int currentRemainder = remainder;
            threadsList.add(new Thread(new Runnable() { public void run() { SimpleSyncMultiThread(threadID, max, end, currentRemainder); } }));
            remainder = (remainder == 0) ? 1 : 0;
        }

        for (Thread x : threadsList) {
            x.start();
        }
/*
        try {
            for (Thread x : threadsList) {
                x.join(timeout);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 */
    }

    /**
     * Instantiates a new thread.
     *
     * @param threadID     the thread ID
     * @param max          the max number
     * @param end          the end time
     * @param remainder    the division remainder
     */
    public void SimpleSyncMultiThread(long threadID, long max, long end, int remainder) {
        long fetchStartTime = System.currentTimeMillis();
        System.out.println("Thread" + threadID + ": start");
        synchronized (this) {
            while (currentNum <= max) {
                while (currentNum % 2 != remainder) {
                    if (currentNum >= max) {
                        break;
                    } else {
                        try {
                            wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("Thread" + threadID + ": " + currentNum + ", remainder = " + remainder);
                if ((System.currentTimeMillis() >= end) || (currentNum >= max)) {
                    break;
                } else {
                    currentNum++;
                    notifyAll();
                }
            }
        }
        System.out.println("It took " + (fetchStartTime - System.currentTimeMillis()) / 1000 + " seconds to run " + "Thread" + threadID);
    }
}