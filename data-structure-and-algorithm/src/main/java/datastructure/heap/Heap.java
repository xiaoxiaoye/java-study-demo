package datastructure.heap;

/**
 * Heap实现为大顶堆
 */
public class Heap {
    private int capacity;
    private int count;
    private int[] items;

    public Heap(int capacity) {
        this.capacity = capacity;
        this.items = new int[capacity + 1];
        this.count = 0;
    }

    // 插入元素
    public void insert(int data) {
        // 堆满
        if (count == capacity) {
            return;
        }
        items[++count] = data;

        // 从下往上堆化
        int i = count;
        while ((i / 2 > 0) && (items[i] > items[i / 2])) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    private void swap(int i, int j) {
        int tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    // 删除堆顶元素
    public void removeMax() {
        // 堆空
        if (count == 0) {
            return;
        }

        // 将堆顶元素替换为最后一个元素
        items[1] = items[count];
        count--;

        // 从上往下堆化，可以避免数据空洞
        int i = 1;
        while (true) {
            int maxPos = i;
            if ((i * 2) <= capacity && items[i] < items[i * 2]) {
                maxPos = i * 2;
            }
            if ((i * 2 + 1) <= capacity && items[i] < items[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (i == maxPos) break;

            swap(i, maxPos);
            i = maxPos;
        }
    }
}
