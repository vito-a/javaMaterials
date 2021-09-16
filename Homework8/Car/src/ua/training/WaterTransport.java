package ua.training;

public abstract class WaterTransport implements Transport {

    @Override
    public void move() {
        System.out.println("we are sailing");
    }
}
