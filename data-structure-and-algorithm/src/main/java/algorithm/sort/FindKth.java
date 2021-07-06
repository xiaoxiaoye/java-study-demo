package algorithm.sort;

public class FindKth {
    public static int findKth(int[] nums, int k) {
        int begin = 0;
        int end = nums.length-1;
        while (true) {
            int p = partition(nums, begin, end);
            if (p == k) {
                return nums[p];
            } else if (p < k) {
                begin = p + 1;
            } else {
                end = p - 1;
            }
        }
    }

    public static int partition(int[] nums, int p, int r) {
        int pivot = nums[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (nums[j] < pivot) {
                swap(nums, j, i);
                i++;
            }
        }
        swap(nums, i, r);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {9,2,3,4,5};
        int r = FindKth.findKth(nums, 0);
        System.out.println(r);
    }
}
