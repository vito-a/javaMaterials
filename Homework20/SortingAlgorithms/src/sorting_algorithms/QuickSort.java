package sorting_algorithms;

public class QuickSort implements SortingAlgorithm {
    private int delay = GUIComponents.delaySlider.getValue() * 1000;

    @Override
    public String getName() {
        return "Quick Sort";
    }

    @Override
    public void doSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private int partition(int nums[], int low, int high) {
        int pivot = nums[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (nums[j] < pivot) {
                i++;

                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                SortingAlgorithm.setCurrentBar(j);
                SortingAlgorithm.sleepFor(delay);
            }
        }

        int temp = nums[i + 1];
        nums[i + 1] = nums[high];
        nums[high] = temp;

        return i + 1;
    }

    private void sort(int nums[], int low, int high) {
        if (low < high) {
            int pi = partition(nums, low, high);

            sort(nums, low, pi - 1);
            sort(nums, pi + 1, high);
        }
    }

    @Override
    public void changeDelay(int delay) {
        this.delay = delay;
    }
}