package ua.training.model.entity;

/**
 * The Periodical entity.
 */
public class Periodical {
    private Long id;
    private String name;
    private String description;
    private Long catId;
    private Double price;

    public Periodical() {
    }

    public Periodical(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", catId='" + catId + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
