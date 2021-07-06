package algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序, 不稳定的排序
 */
public class Quick {
    public static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int p, int r) {
        if (p >= r) {
            return;
        }

        int pivot = partition(nums, p, r); // 这里将数据分成三个部分，0...pivot-1, pivot, pivot+1...r
        quickSort(nums, p, pivot-1); // 这里是pivot-1 不是pivot， 不然会造成死循环
        quickSort(nums, pivot+1, r);
    }

    private static int partition(int[] nums, int p, int r) {
        int value = nums[r];

        int i = p;
        for (int j = p; j < r; j++) {
            if (nums[j] < value) {
                swap(nums, i,j);
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
        int[] nums = new int[]{9, 6, 4, 3, 1, 4, 5, 6};
        Quick.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
