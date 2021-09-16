package ua.training;
import java.util.Objects;

public class Triangle extends Shape {

    private Point p1;
    private Point p2;
    private Point p3;

    private double p1p2;
    private double p2p3;
    private double p3p1;

    private double perimeter;
    private double square;

    public Triangle(Point p1, Point p2, Point p3) {
        Objects.requireNonNull(p1);
        Objects.requireNonNull(p2);
        Objects.requireNonNull(p3);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(double oneX, double oneY,
                      double twoX, double twoY,
                      double threeX, double threeY) {
        this.p1 = new Point(oneX, oneY);
        this.p2 = new Point(twoX, twoY);
        this.p3 = new Point(threeX, threeY);
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p) {
        this.p1 = p;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p) {
        this.p2 = p;
    }

    public Point getP3() {
        return p3;
    }

    public void setC(Point p) {
        this.p3 = p;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeterValue) {
        this.perimeter = perimeterValue;
    }

    @Override
    public double square() {
        square = Math.sqrt(perimeter * (perimeter - p1p2) * (perimeter - p2p3) * (perimeter - p3p1));
        return square;
    }

    @Override
    public double perimeter() {
        p1p2 = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) +
                Math.pow(p1.getY() - p2.getY(), 2));
        p2p3 = Math.sqrt(Math.pow(p2.getX() - p3.getX(), 2) +
                Math.pow(p2.getY() - p3.getY(), 2));
        p3p1 = Math.sqrt(Math.pow(p3.getX() - p1.getX(), 2) +
                Math.pow(p3.getY() - p1.getY(), 2));
        perimeter = 0.5 * (p1p2 + p2p3 + p3p1);

        return perimeter;
    }

    @Override
    public void move(double dx, double dy) {
        p1.setX(p1.getX() + dx);
        p1.setY(p1.getY() + dy);
        p2.setX(p2.getX() + dx);
        p2.setY(p3.getY() + dy);
        p3.setX(p3.getX() + dx);
        p3.setY(p3.getY() + dy);
    }

    @Override
    public String toString() {
        return "Triangle {" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                super.toString() + '}';
    }
}
