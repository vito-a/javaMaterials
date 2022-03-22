package ua.training.model;

public class CatalogItemComparator implements java.util.Comparator<CatalogItem> {
    private int comparisonAlgorithm = 1;

    public void setComparisonAlgorithm(int comparisonAlgorithm) {
        this.comparisonAlgorithm = comparisonAlgorithm;
    }

    @Override
    public int compare(CatalogItem o1, CatalogItem o2) {
        switch (comparisonAlgorithm) {
            case 1:
                return java.util.Comparator.comparing(CatalogItem::getName)
                        // .thenComparingBigDecimal(CatalogItem::getPrice)
                        .thenComparing(CatalogItem::getCategory)
                        .thenComparingInt(CatalogItem::getID)
                        .compare(o1, o2);
            case 2:
                int i = o1.getName().compareTo(o2.getName());
                if (i != 0) return i;

                i = o1.getPrice().compareTo(o2.getPrice());
                if (i != 0) return i;

                i = o1.getCategory().compareTo(o2.getCategory());
                if (i != 0) return i;

                return Integer.compare(o1.getID(), o2.getID());
            case 3:
                int[] comparisonResults = {
                        o1.getName().compareTo(o2.getName()), // name comparison
                        o1.getPrice().compareTo(o2.getPrice()), // price comparison
                        o1.getCategory().compareTo(o2.getCategory()), // category comparison
                        Integer.compare(o1.getID(), o2.getID()) // id comparison
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
