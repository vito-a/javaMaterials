package ua.training.model;

import java.math.BigDecimal;
import java.util.Comparator;

public class CatalogItem implements Comparable<CatalogItem> {
    private int id;
    private String name;
    private BigDecimal price;
    private String category;
    private int comparisonAlgorithm = 1;

    public CatalogItem(int id, String name, BigDecimal price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public void setComparisonAlgorithm(int comparisonAlgorithm) {
        this.comparisonAlgorithm = comparisonAlgorithm;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return String.format("CatalogItem(Name: \"%s\", Price: %s, Category: %s, ID: %d)", getName(), getPrice().toString(), getCategory(), getID());
    }

    @Override
    public int compareTo(CatalogItem o) {
        switch (comparisonAlgorithm) {
            case 1:
                return Comparator.comparing(CatalogItem::getName)
                        .thenComparing(CatalogItem::getPrice)
                        .thenComparing(CatalogItem::getCategory)
                        .thenComparingInt(CatalogItem::getID)
                        .compare(this, o);
            case 2:
                int i = name.compareTo(o.getName());
                if (i != 0) return i;

                i = price.compareTo(o.getPrice());
                if (i != 0) return i;

                i = category.compareTo(o.getCategory());
                if (i != 0) return i;

                return Integer.compare(this.id, o.getID());
            case 3:
                int[] comparisonResults = {
                        this.name.compareTo(o.getName()), // name comparison
                        this.price.compareTo(o.getPrice()), // price comparison
                        this.category.compareTo(o.getCategory()), // category comparison
                        Integer.compare(this.id, o.getID()) // id comparison
                };
                for (int j : comparisonResults) {
                    if (j != 0) {
                        return j;
                    }
                }
                return 0;
            default:
                break;
        }

        return 0;
    }
}