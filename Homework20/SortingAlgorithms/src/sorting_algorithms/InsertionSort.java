package sorting_algorithms;

/**
 * Implementation of the insertion sort.
 */

public class InsertionSort implements SortingAlgorithm {
    private int delay = GUIComponents.delaySlider.getValue() * 1000;

    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public void doSort(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; ++i) {
            int key = nums[i];
            int j = i - 1;

            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;

            SortingAlgorithm.setCurrentBar(j);
            SortingAlgorithm.sleepFor(delay);
        }
    }

    @Override
    public void changeDelay(int delay) {
        this.delay = delay;
    }
}