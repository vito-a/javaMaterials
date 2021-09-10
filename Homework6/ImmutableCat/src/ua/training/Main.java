package ua.training;

public class Main {

    public static void main(String[] args) {
        ImmutableCat first = new ImmutableCat("Bengal", "green");
        ImmutableCat second = first.setBreed("Sphynx");
    }
}
