package ua.training;

public class Driver implements CarDriver {
    private DriverLicense license;
    private String name;

    public Driver(String name, DriverLicense license) {
        this.license = license;
        this.name = name;
    }

    @Override
    public DriverLicense getDriverLicense() {
        return license;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDriverLicense(DriverLicense license) {
        this.license = license;
    }

    public DriverLicense getLicense() {
        return license;
    }
}
