package ua.training;

import ua.training.model.CatalogItem;
import ua.training.model.PriceComparator;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Catalog {
    // File containing our catalog
    public static final File CATALOG_FILE = new File("src/main/resources/catalog.csv");
    public static void main(String[] args) {
        // First we are reading the CSV file and turn the data inside into a list containing our catalog items.
        List<CatalogItem> catalog = new ArrayList<CatalogItem>();
        try {
            List<String> lines = Files.readAllLines(CATALOG_FILE.toPath());
            lines.remove(0);
            String[] splitLine;
            for (String line : lines) {
                splitLine = line.split(",");
                catalog.add(new CatalogItem(Integer.parseInt(splitLine[0]), splitLine[1], new BigDecimal(splitLine[2]), splitLine[3]));
            }
        } catch (IOException e) {
            System.err.println("File could not be read.");
            e.printStackTrace();
            return;
        } catch (NumberFormatException e) {
            System.err.println("Catalog CSV is corrupted.");
            e.printStackTrace();
            return;
        }
        // After the catalog is loaded, we randomly scramble the ordering of the list.
        // Note that we are seeding the random shuffling so that we always get the same
        // result on consecutive executions with the same data.
        Collections.shuffle(catalog, new Random(20220310));

        // Next we have a look at the data.
        printCatalog(catalog);

        // TASK AREA START

        System.out.println("\n\nComparable:");
        long fetchStartTime = System.currentTimeMillis();
        System.out.println("\n\n1. Comparable Sorted with Algorithm 1:");
        for (CatalogItem item : catalog) {
            item.setComparisonAlgorithm(1);
        }
        Collections.sort(catalog);
        printCatalog(catalog);
        System.out.println("\n Sorting with algorithm 1 took:" + (System.currentTimeMillis() - fetchStartTime) + " ms. \n");

        fetchStartTime = System.currentTimeMillis();
        System.out.println("\n\n2. Comparable Sorted with Algorithm 2:");
        for (CatalogItem item : catalog) {
            item.setComparisonAlgorithm(2);
        }
        Collections.sort(catalog);
        printCatalog(catalog);
        System.out.println("\n Sorting with algorithm 2 took:" + (System.currentTimeMillis() - fetchStartTime) + " ms. \n");

        System.out.println("\n\n3. Comparable Sorted with Algorithm 3:");
        for (CatalogItem item : catalog) {
            item.setComparisonAlgorithm(3);
        }
        Collections.sort(catalog);
        printCatalog(catalog);
        System.out.println("\n Sorting with algorithm 3 took:" + (System.currentTimeMillis() - fetchStartTime) + " ms. \n");

        System.out.println("\n\nComparator:");
        fetchStartTime = System.currentTimeMillis();
        System.out.println("\n\n1. Comparator Sorted with Algorithm 1:");
        PriceComparator comparator = new PriceComparator();
        comparator.setComparisonAlgorithm(1);
        Collections.sort(catalog, comparator);
        catalog.sort(new PriceComparator());
        printCatalog(catalog);
        System.out.println("\n Sorting with algorithm 1 took:" + (System.currentTimeMillis() - fetchStartTime) + " ms. \n");

        fetchStartTime = System.currentTimeMillis();
        System.out.println("\n\n2. Comparator Sorted with Algorithm 2:");
        comparator = new PriceComparator();
        comparator.setComparisonAlgorithm(2);
        Collections.sort(catalog, comparator);
        catalog.sort(new PriceComparator());
        printCatalog(catalog);
        System.out.println("\n Sorting with algorithm 2 took:" + (System.currentTimeMillis() - fetchStartTime) + " ms. \n");

        // TASK ARE END
    }

    /**
     * Utility method to print the catalog in a table view.
     * @param catalog The catalog list to display.
     */
    private static void printCatalog(List<CatalogItem> catalog) {
        System.out.println("ID\tName\t\tPrice\tCategory");
        catalog.forEach((item) -> System.out.println(item.getID() + "\t" + item.getName() + ((item.getName().length() > 8) ? "\t" : "\t\t") + item.getPrice().toString() + "\t" + item.getCategory()));
    }
}
