package algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序, 不稳定的排序
 */
public class Selection {
    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            int tmp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9, 7, 5, 4, 3, 1};
        Selection.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
