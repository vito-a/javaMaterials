package sorting_algorithms;

public class MergeSort implements SortingAlgorithm {
    private int delay = GUIComponents.delaySlider.getValue() * 1000;

    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public void doSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void merge(int nums[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for(int i = 0; i < n1; ++i)
            L[i] = nums[l + i];

        for(int j = 0; j < n2; ++j)
            R[j] = nums[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                nums[k] = L[i];
                i++;

                SortingAlgorithm.setCurrentBar(j);
                SortingAlgorithm.sleepFor(delay);

            } else {
                nums[k] = R[j];
                j++;

                SortingAlgorithm.setCurrentBar(j);
                SortingAlgorithm.sleepFor(delay);
            }
            k++;
        }

        while (i < n1) {
            nums[k] = L[i];
            i++;
            k++;

            SortingAlgorithm.setCurrentBar(j);
            SortingAlgorithm.sleepFor(delay);
        }

        while (j < n2) {
            nums[k] = R[j];
            j++;
            k++;

            SortingAlgorithm.setCurrentBar(j);
            SortingAlgorithm.sleepFor(delay);
        }
    }

    private void sort(int nums[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            sort(nums, l, m);
            sort(nums, m + 1, r);

            merge(nums, l, m, r);
        }
    }

    @Override
    public void changeDelay(int delay) {
        this.delay = delay;
    }
}