package ua.training;

public class Wheel {
    private int wheelDiameter;

    public Wheel(int wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
    }

    public void changeWheel(WheelCar car) {
        if (car.wheel().getWheelDiameter() == this.wheelDiameter) {
            car.setWheel(this);
            System.out.println("Wheel changed.");
            return;
        }
        System.out.println("Incompatible wheel diameter. Not replaced.");
    }

    public int getWheelDiameter() {
        return wheelDiameter;
    }

    public void setWheelDiameter(int wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
    }
}
