package datastructure.stack;

/**
 * @program: data-structure-and-algorithm
 * 基于数组实现的顺序栈
 * @author: Yejiaxin
 * @create: 2020-08-05 17:42
 */
public class ArrayStack {
    String[] items;
    int capacity;
    int count;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.items = new String[capacity];
        this.count = 0;
    }

    public boolean put(String item) {
        if (count >= capacity) {
            return false;
        }

        items[count++] = item;
        return true;
    }

    public String pop() {
        if (count <= 0) {
            return null;
        }

        return items[count--];
    }
}