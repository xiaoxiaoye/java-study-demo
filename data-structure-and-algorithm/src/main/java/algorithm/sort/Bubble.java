package algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序, 稳定排序
 */
public class Bubble {
    public static void sort(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            boolean isSwap = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                    isSwap = true;
                }
            }
            if (!isSwap) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 6, 4, 36, 79};

        Bubble.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
