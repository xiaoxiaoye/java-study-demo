package datastructure.list;

/**
 * @program: data-structure-and-algorithm
 * 基于链表的LRU缓存实现方案
 * @author: Yejiaxin
 * @create: 2020-08-04 14:09
 */
public class LRUCacheImplementByLinkedList {
    class ListNode {
        public int val;
        public int key;

        public ListNode next;

        public ListNode(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }

    // 首节点
    ListNode dummyFirst;
    // 缓存容量
    int capacity;
    // 当前使用量
    int counts;

    public LRUCacheImplementByLinkedList(int capacity) {
        this.capacity = capacity;
        this.dummyFirst = new ListNode(-1, -1);
    }

    public int get(int key) {
        ListNode prev = dummyFirst;
        ListNode current = dummyFirst.next;
        while (current != null) {
            if (current.key == key) {
                // 找到节点后，将节点插入头结点之前
                prev.next = current.next;
                current.next = dummyFirst.next;
                dummyFirst.next = current;

                return current.val;
            }
            prev = current;
            current = current.next;
        }
        return -1;
    }

    public void put(int key, int val) {
        // 先看缓存中是否存在该节点， 有的话直接返回，并将该节点移动到头结点
        ListNode prev= null;
        ListNode current = dummyFirst;
        while (true) {
            if (current.key == key) {
                current.val = val;

                // 插入头结点
                prev.next = current.next;
                current.next = dummyFirst.next;
                dummyFirst.next = current;
                return;
            }

            if (current.next == null) break;

            prev = current;
            current = current.next;
        }

        // 缓存满了，删除最后一个节点
        if (counts == capacity) {
           prev.next = null;
        }

        // 缓存中不存在，直接将节点添加到首节点
        ListNode node = new ListNode(key, val);
        node.next = dummyFirst.next;
        dummyFirst.next = node;
        if (++counts > capacity) {
            counts = capacity;
        }
    }


    public static void main(String[] args) {
        LRUCacheImplementByLinkedList cache = new LRUCacheImplementByLinkedList(2);

        int value = -1;
        cache.put(1, 1);
        cache.put(2, 2);
        value = cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        value = cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        value = cache.get(1);       // 返回 -1 (未找到)
        value = cache.get(3);       // 返回  3
        value = cache.get(4);       // 返回  4
    }
}
