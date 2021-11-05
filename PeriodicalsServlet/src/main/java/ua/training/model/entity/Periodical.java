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

    /**
     * Instantiates a new Periodical.
     */
    public Periodical() {
    }

    /**
     * Instantiates a new Periodical.
     *
     * @param id   the id
     * @param name the name
     */
    public Periodical(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    /**
     * Gets periodical id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets periodical id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets periodical name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets periodical name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets periodical description.
     *
     * @return the description
     */
    public String getDescription() { return description; }

    /**
     * Sets periodical description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets periodical category id.
     *
     * @return the cat id
     */
    public Long getCatId() {
        return catId;
    }

    /**
     * Sets periodical category id.
     *
     * @param catId the cat id
     */
    public void setCatId(Long catId) {
        this.catId = catId;
    }

    /**
     * Gets periodical price.
     *
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets periodical price.
     *
     * @param price the price
     */
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
