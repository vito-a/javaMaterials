package ua.training.model.entity;

/**
 * The Category entity.
 */
public class Category {
    private long catId;
    private String name;

    /**
     * Instantiates a new Category.
     */
    public Category() {
    }

    /**
     * Instantiates a new Category.
     *
     * @param catId the cat id
     * @param name  the name
     */
    public Category(Long catId, String name) {
        this.name = name;
        this.catId = catId;
    }

    /**
     * Gets category id.
     *
     * @return the cat id
     */
    public long getCatId() {
        return catId;
    }

    /**
     * Sets category id.
     *
     * @param catId the cat id
     */
    public void setCatId(long catId) {
        this.catId = catId;
    }

    /**
     * Gets category name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets category name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
