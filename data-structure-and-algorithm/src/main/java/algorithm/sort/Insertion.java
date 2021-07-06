package algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序, 稳定排序
 */
public class Insertion {
    public static void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (nums[j] < value) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 5, 4, 3};
        Insertion.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
