package sorting_algorithms;

/**
 * Implementation of the selection sort.
 */

public class SelectionSort implements SortingAlgorithm {
    private int delay = GUIComponents.delaySlider.getValue() * 1000;

    @Override
    public String getName() {
        return "Selection Sort";
    }

    @Override
    public void doSort(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;

                    SortingAlgorithm.setCurrentBar(i);
                    SortingAlgorithm.setCurrentBar(j);
                    SortingAlgorithm.sleepFor(delay);
                }

            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
    }

    @Override
    public void changeDelay(int delay) {
        this.delay = delay;
    }
}