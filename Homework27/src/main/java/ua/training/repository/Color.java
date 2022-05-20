package ua.training.repository;

public interface Color {
    String color = null;

    default void design() {
        System.out.println(color);
    }
    String getColor();
}
