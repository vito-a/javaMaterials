package ua.training;

public class Refueler {
    String name;

    public Refueler(String name) {
        this.name = name;
    }

    public void refuelCar(RefuelCar car) {
        if (!car.refueled()) {
            car.setRefueled(true);
            System.out.println("Refueled.");
            return;
        }
        System.out.println("The tank is full. Not refueled.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
