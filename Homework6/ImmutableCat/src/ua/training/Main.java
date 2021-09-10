/*
 *  Java Autumn 2021 - 07. Class Object. Nested classes. Enums - ImmutableObject - the immutable object adding
 *
 * Main
 *
 */
package ua.training;

public class Main {

    public static void main(String[] args) {
        ImmutableCat first = new ImmutableCat("Bengal", "green");
        ImmutableCat second = first.setBreed("Sphynx");
    }
}
