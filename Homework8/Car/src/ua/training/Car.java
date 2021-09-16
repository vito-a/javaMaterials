package ua.training;

public class Car extends GroundTransport implements Transport, RefuelCar, WheelCar {
    public static final int CITY_LIMIT = 60;
    private boolean inCity;
    private double weight;
    private int maxSpeed;
    private DriverLicense.Category vehicleCategory;
    private CarBattery battery;
    private boolean isRefueled;
    private Wheel wheel;

    public Car(double weight, int maxSpeed,
               DriverLicense.Category vehicleCategory,
               CarBattery battery, boolean isRefueled,
               Wheel wheel) {
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.vehicleCategory = vehicleCategory;
        this.battery = battery;
        this.isRefueled = isRefueled;
        this.wheel = wheel;
    }

    public Car(int maxSpeed, boolean inCity) {
        super(maxSpeed);
        //this.maxSpeed = maxSpeed;
        setMaxSpeed(maxSpeed);
        this.inCity = inCity;
    }

    public Car() {
        super();
    }

    @Override
    public int speed() {
        return inCity ? CITY_LIMIT : getMaxSpeed();
    }

    public void drive(CarDriver driver) {
        if (driver.getDriverLicense().getCategory() == vehicleCategory) {
            System.out.println("Driving...");
        } else {
            System.out.println("Driver's license does not allow them to drive this car.");
        }
    }

    public void changeBattery(CarBattery battery) {
        if (!(battery.width() == this.battery.width() &&
                battery.height() == this.battery.height() &&
                battery.length() == this.battery.length())) {
            System.out.println("Incompatible battery. Not replaced.");
            return;
        }

        this.battery = battery;

        System.out.println("Battery replaced.");
    }

    @Override
    public Wheel wheel() {
        return wheel;
    }

    @Override
    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    @Override
    public boolean refueled() {
        return isRefueled;
    }

    @Override
    public void setRefueled(boolean refueled) {
        this.isRefueled = refueled;
    }

    public void sellCar() {
        System.out.println("Car is sold.");
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public CarBattery getBattery() {
        return battery;
    }

    public DriverLicense.Category getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(DriverLicense.Category vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setBattery(CarBattery battery) {
        this.battery = battery;
    }


}
