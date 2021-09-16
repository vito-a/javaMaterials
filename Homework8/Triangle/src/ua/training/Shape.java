package ua.training;

public abstract class Shape {
    public abstract double square();
    public abstract double perimeter();
    public abstract void move(double dx, double dy);

    @Override
    public String toString() {
        return ", perimeter = " + perimeter() +", square = " +square();
    }
}
