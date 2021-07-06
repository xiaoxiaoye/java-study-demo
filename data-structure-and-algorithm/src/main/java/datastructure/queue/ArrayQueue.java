package datastructure.queue;

/**
 * @program: data-structure-and-algorithm
 * TODO
 * @author: Yejiaxin
 * @create: 2020-08-07 09:41
 */
public class ArrayQueue {
    private int capacity;
    private int head;
    private int tail;

    private String[] items;

    public ArrayQueue(int capacity) {
        this.head = 0;
        this.tail = 0;
        this.capacity = capacity;
        this.items = new String[capacity];
    }

    public boolean enqueue(String item) {
        // 队列未满, tail 没有达到数组末
        if (tail < capacity) {
            items[tail++] = item;
            return true;
        }

        // tail 到数据末端， 但是数组没有满， 数组整体左移
        if (head > 0) {
            for (int i = head; i < tail; i++) {
                items[i - head] = items[head];
            }
            head = 0;
            tail = tail-head;

            items[tail++] = item;
            return true;
        }

        return false;
    }

    public String dequeue() {
        if (head < tail) {
            return items[head++];
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(2);

        boolean r1 = queue.enqueue("1");
        System.out.println(r1);
        r1 = queue.enqueue("2");
        System.out.println(r1);
        r1 = queue.enqueue("3");
        System.out.println(r1);

        String r2 = queue.dequeue();
        System.out.println(r2);
        r2 = queue.dequeue();
        System.out.println(r2);
        r2 = queue.dequeue();
        System.out.println(r2);
    }

}
