package datastructure.heap;

/**
 * Leetcode 215题， 数组中第K个最大元素
 * 思路：
 *  1. 利用数组前K个元素建立小顶堆， 遍历第K个元素往后的所有元素，如果比堆顶元素大， 交换元素，然后从上往下堆化。
 *  遍历完成后， 堆顶元素就是第K个最大元素
 */

public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        buildHeap(nums, k);

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[0]) {
                swap(nums, 0, i);
                heapify(nums, 0, k);
            }
        }
        return nums[0];
    }

    // 利用前K个元素建堆
    private void buildHeap(int[] nums, int k) {
        for (int i = k / 2 - 1; i >= 0; i--) {
            heapify(nums, i, k);
        }
    }

    // 堆化
    private void heapify(int[] nums, int pos, int end) {
        int j = pos;
        while (true) {
            int minPos = j;
            if (j * 2 + 1 < end && nums[j * 2 + 1] < nums[minPos]) minPos = j * 2 + 1;
            if (j * 2 + 2 < end && nums[j * 2 + 2] < nums[minPos]) minPos = j * 2 + 2;

            if (minPos == j) break;
            swap(nums, j, minPos);
            j = minPos;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        FindKthLargest s = new FindKthLargest();
        int[] c1 = new int[]{3,2,1,5,6,4};
        int r1 = s.findKthLargest(c1, 2);
        System.out.println(r1);
    }
}
