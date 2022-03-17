package ua.training.service;

public class SimpleSyncNumberTwoThreadService {
    long counter = 1;
    final long max;

    public SimpleSyncNumberTwoThreadService(long max) {
        this.max = max;
    }

    public void printOddNumber() {
        synchronized (this) {
            while (counter < max) {
                while (counter % 2 == 0) {
                    try {
                        wait();
                    }
                    catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Odd thread: " + counter);
                counter++;
                notify();
            }
        }
    }

    public void printEvenNumber() {
        synchronized (this) {
            while (counter < max) {
                while (counter % 2 == 1) {
                    try {
                        wait();
                    }
                    catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Even thread" + counter);
                counter++;
                notify();
            }
        }
    }
}
