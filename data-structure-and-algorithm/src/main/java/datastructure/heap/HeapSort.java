package datastructure.heap;

import java.util.Arrays;

/**
 * 堆排序，思路如下：
 * 1. 整个数组先堆化， 从中间位置开始，从后往前，每个元素依次从上往下堆化
 * 2. 将最大元素依次从堆中取出，放到最后
 */

public class HeapSort {
    public void sort(int[] numbers) {
        buildHeap(numbers);
        for (int i = numbers.length - 1; i > 0; i--) {
            swap(numbers, 0, i);
            heapify(numbers, 0, i);
        }
    }

    // 建堆
    private void buildHeap(int[] numbers) {
        for (int i = (numbers.length / 2 - 1); i >= 0; i--) {
            heapify(numbers, i, numbers.length);
        }
    }

    // 从pos位置开始，从上向下堆化
    private void heapify(int[] numbers, int pos, int end) {
        int j = pos;
        while (true) {
            int maxPos = j;
            if ((j * 2 + 1) < end && numbers[j * 2 + 1] > numbers[maxPos]) maxPos = j * 2 + 1;
            if ((j * 2 + 2) < end && numbers[j * 2 + 2] > numbers[maxPos]) maxPos = j * 2 + 2;
            if (maxPos == j) break;

            swap(numbers, maxPos, j);
            j = maxPos;
        }
    }

    private void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }

    public static void main(String[] args) {
        HeapSort s = new HeapSort();
        int[] c1 = new int[]{0,2, 1, 3, 5, 0,4, 8,9,4,2};
        s.sort(c1);
        System.out.println(Arrays.toString(c1));
    }
}
