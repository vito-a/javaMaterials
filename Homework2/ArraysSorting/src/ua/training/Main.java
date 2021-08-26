package ua.training;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] sourceArray = {3, 5, 4, 6, 5, 3, 4, 5, 7, 3, 2};
        int[] bubbleIntArray = sourceArray.clone(), insertionIntArray = sourceArray.clone();

        System.out.println("Original: " + Arrays.toString(sourceArray));

        long startTime = System.nanoTime();
        bubbleSort(bubbleIntArray);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Bubble: " + Arrays.toString(bubbleIntArray));
        System.out.println("Bubble duration: " + duration);  // nanoseconds

        startTime = System.nanoTime();
        insertionSort(insertionIntArray);
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("Insertion: " + Arrays.toString(insertionIntArray));
        System.out.println("Bubble duration: " + duration);  // nanoseconds
    }

    private static void bubbleSort(int[] barray) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < barray.length-1; i++) {
                if (barray[i] > barray[i+1]){
                    isSorted = false;
                    swap(barray, i);
                }
            }
        }
    }

    private static void insertionSort(int[] barray) {
        for (int i = 0 ; i <= barray.length - 1; i++){
            for (int j = 0 ; j < barray.length - 1 - i; j++){
                conditionalSwap(barray, j);
            }
        }
    }

    private static void conditionalSwap(int[] barray, int i) {
        if (barray[i] > barray[i+1]){
            swap(barray, i);
        }
    }

    private static void swap(int[] barray, int i) {
        int tmp = barray[i];
        barray[i] = barray[i+1];
        barray[i+1] = tmp;
    }
}
