package ua.training.service;

import java.util.ArrayList;

/**
 * The Number test thread service.
 */
public class SequentalNumberThreadService {
    long threadsNumber;
    long max;
    long end;

    class Control {
        public volatile boolean flag = false;
        public volatile long currentNum = 0;
    }
    final Control control = new Control();

    /**
     * Instantiates a new File test thread service.
     *
     * @param threadsNumber  the threads number
     * @param max            the max number
     * @param end            the end time
     */
    public SequentalNumberThreadService(long threadsNumber, long max, long end) {
        this.threadsNumber = threadsNumber;
        this.max = max;
        this.end = end;
        int remainder = 0;

        ArrayList<Thread> threadsList = new ArrayList<Thread>();

        for (int threadNum = 0; threadNum < threadsNumber; threadNum++) {
            threadsList.add(new Thread(new SequentalThread(threadNum, max, end, remainder)));
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
     * The File test thread.
     */
    class SequentalThread implements Runnable {
        private final long threadID;
        private long currentNum;
        private final long max;
        private final long end;
        int remainder;

        /**
         * Instantiates a new thread.
         *
         * @param threadID     the thread ID
         * @param max          the max number
         * @param end          the end time
         */
        public SequentalThread(long threadID, long max, long end, int remainder) {
            this.threadID = threadID;
            this.max = max;
            this.end = end;
            this.remainder = remainder;
        }

        @Override
        public void run() {
            long fetchStartTime = System.currentTimeMillis();
            System.out.println("Thread" + threadID + ": start");
            while (control.currentNum <= max) {
                synchronized (control) {
                    while (control.currentNum % 2 != remainder) {
                        try {
                            control.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread" + threadID + ": " + control.currentNum + ", remainder = " + remainder);
                    if ((System.currentTimeMillis() >= end) || (control.currentNum >= max)) {
                        break;
                    } else {
                        control.currentNum++;
                    }
                    control.notifyAll();
                }
            }
            System.out.println("It took " + (fetchStartTime - System.currentTimeMillis()) / 1000 + " seconds to run " + "Thread" + threadID);
        }
    }
}