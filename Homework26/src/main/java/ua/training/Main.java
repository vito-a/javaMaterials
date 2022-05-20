package ua.training;

import ua.training.model.CustomArrayListClass;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        // main.Task1();
        main.Task2();
    }

    /**
     *
     */
    public void Task1 (){
        CustomArrayListClass<Integer> customArrayList = new CustomArrayListClass<>();
        // Add elements into custom ArrayList
        customArrayList.add(1);
        customArrayList.add(2);
        customArrayList.add(3);
        customArrayList.add(4);
        customArrayList.add(1);
        customArrayList.add(2);

        // Display custom ArrayList
        System.out.println(customArrayList + "\n");
        System.out.println("Element at index 1 = " + customArrayList.get(1));

        // Remove 1st element from custom ArrayList
        System.out.println("Element removed from index 2 = " + customArrayList.removeAtIndex(2));
        System.out.println(customArrayList + "\n");

        // both should throw IndexOutOfBoundsException, there is no element at index 11.
        // customArrayList.remove(11);
        // customArrayList.get(11);
    }

    /**
     *
     */
    public void Task2 (){
        CustomArrayListClass<Integer> customArrayList = new CustomArrayListClass<>(20);
        System.out.println(customArrayList + "\n");

        // Add elements into custom ArrayList by index
        System.out.println("Adding 10 elements");
        customArrayList.add(1, 12);
        customArrayList.add(5, 121);
        customArrayList.add(10, 151);
        customArrayList.add(13, 15);
        customArrayList.add(15, 89);
        customArrayList.add(11, 145);
        customArrayList.add(2, 565);
        customArrayList.add(8, 345);
        customArrayList.add(9, 785);
        customArrayList.add(12, 489);
        System.out.println(customArrayList + "\n");

        // Remove 4 elements from the custom ArrayList
        System.out.println("Removing 4 elements");
        customArrayList.remove(12);
        customArrayList.remove(121);
        customArrayList.remove(151);
        customArrayList.remove(15);
        System.out.println(customArrayList);
    }
}
