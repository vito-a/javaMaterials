package ua.training;

public class Main {
    public static void main(String[] args) {
        Transport transport = new Car(150, false);
        transport.move();
        System.out.println("speed = " + transport.speed());

        System.out.println();

        transport = new Car(180, true);
        transport.move();
        System.out.println("speed = " + transport.speed());

        System.out.println();

        transport = new UBoat(true);
        transport.move();
        System.out.println("speed = " + transport.speed());

        System.out.println();

        ((UBoat) transport).setUnderWater(false);
        transport.move();
        System.out.println("speed = " + transport.speed());

        Wheel wheel1 = new Wheel(13);
        Battery battery1 = new Battery(60, 150, 100, 40, "Bosch");

        Car car = new Car(3500, 180, DriverLicense.Category.A, battery1, false, wheel1);

        DriverLicense driverLicense1 = new DriverLicense(DriverLicense.Category.C, 12345);
        Driver driver1 = new Driver("Igor", driverLicense1);
        car.drive(driver1);

        DriverLicense driverLicense2 = new DriverLicense(DriverLicense.Category.A, 67890);
        Driver driver2 = new Driver("Alexander", driverLicense2);
        car.drive(driver2);

        System.out.println();

        Battery battery2 = new Battery(50, 150, 80, 25, "Tudor");
        Battery battery3 = new Battery(60, 150, 100, 40, "Varta");

        car.changeBattery(battery2);
        car.changeBattery(battery3);

        System.out.println();

        Refueler refueler1 = new Refueler("Ivan");

        refueler1.refuelCar(car);
        refueler1.refuelCar(car);

        System.out.println();

        Wheel wheel2 = new Wheel(16);
        Wheel wheel3 = new Wheel(13);

        wheel2.changeWheel(car);
        wheel3.changeWheel(car);

        System.out.println();

        car.move();
        System.out.println("max speed = " + car.speed());
    }
}
