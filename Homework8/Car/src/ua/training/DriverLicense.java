package ua.training;

public class DriverLicense {

    public enum Category {A, B, C, D}

    private int id;
    private Category category;

    public DriverLicense(Category category, int id) {
        this.id = id;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }
}
