package algorithm.sort;

import java.util.Arrays;

/**
 * 计数排序
 */
public class Count {
    public static void sort(int[] nums) {
        int max = nums[0];

        // 得到元素最大值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        // 计数
        int[] countNums = new int[max+1];
        for (int i = 0; i < nums.length; i++) {
            countNums[nums[i]]++;
        }

        // 最难理解的点
        for (int i = 1; i < countNums.length; i++) {
            countNums[i] = countNums[i - 1] + countNums[i];
        }

        // 从后往前依次遍历nums,可以保证排序的稳定性
        int[] tmp = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int p = countNums[nums[i]]-1; // 这个地方需要-1， countNums中存储的比当前位置大于等于的个数
            tmp[p] = nums[i];
            countNums[nums[i]]--;
        }

        // 将数据拷贝回原数组
        for (int i = 0; i < tmp.length; i++) {
            nums[i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 3, 0, 2, 3, 0, 3};
        Count.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
