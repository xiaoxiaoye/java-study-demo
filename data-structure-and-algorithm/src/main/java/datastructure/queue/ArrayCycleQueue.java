package datastructure.queue;

/**
 * @program: data-structure-and-algorithm
 * TODO
 * @author: Yejiaxin
 * @create: 2020-08-07 11:11
 */
public class ArrayCycleQueue {
    private final int capacity;
    private int tail;
    private int head;

    private String[] items;

    public ArrayCycleQueue(int capacity) {
        this.capacity = capacity+1;
        this.tail = 0;
        this.head = 0;

        this.items = new String[capacity+1];
    }

    public boolean enqueue(String item) {
        if ((tail + 1) % capacity == head) {
            return false;
        }

        items[tail] = item;
        tail = (tail + 1) % capacity;
        return true;
    }

    public String dequeue() {
        if (tail == head) {
            return null;
        }

        String res = items[head];
        head = (head + 1) % capacity;
        return res;
    }
}
