package ua.training.model.entity;

public class Category {
    private long catId;
    private String name;

    public Category() {
    }

    public Category(Long catId, String name) {
        this.name = name;
        this.catId = catId;
    }

    public long getCatId() {
        return catId;
    }

    public void setCatId(long catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
