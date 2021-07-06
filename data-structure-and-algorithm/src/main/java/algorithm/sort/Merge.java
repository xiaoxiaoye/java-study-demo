package algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序, 稳定排序
 */
public class Merge {
    public static void sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int p, int r) {
        if (p >= r) return;

        int m = (p + r) / 2;
        mergeSort(nums, p, m);
        mergeSort(nums, m + 1, r);
        merge(nums, p, m, r);
    }

    private static void merge(int[] nums, int p, int q, int r) {
        int i = p;
        int j = q + 1;

        int[] tmp = new int[r - p + 1];

        int k = 0;
        while (i <= q && j <= r) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }

        // 将剩余数据按序拷贝到临时数组
        int m = i <= q ? i : j;
        while (k < tmp.length) {
            tmp[k++] = nums[m++];
        }

        for (int n = 0; n < tmp.length; n++) {
            nums[p + n] = tmp[n];
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9, 7, 5, 4, 3, 2, 1};
        Merge.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
