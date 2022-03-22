package ua.training.model;

import java.util.Comparator;

public class PriceComparator implements Comparator<CatalogItem> {
    private int comparisonAlgorithm = 1;

    public void setComparisonAlgorithm(int comparisonAlgorithm) {
        this.comparisonAlgorithm = comparisonAlgorithm;
    }

    @Override
    public int compare(CatalogItem o1, CatalogItem o2) {
        switch (comparisonAlgorithm) {
            case 1:
                return Comparator.comparing(CatalogItem::getPrice).compare(o1, o2);
            case 2:
                return o1.getPrice().compareTo(o2.getPrice());
            default:
                break;
        }

        return 0;
    }
}
