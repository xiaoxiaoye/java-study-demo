package datastructure.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: data-structure-and-algorithm
 * TODO
 * @author: Yejiaxin
 * @create: 2020-08-04 21:19
 */
public class LRUCacheImplementByMapAndList {
    class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // 伪头部和伪尾部，简化操作
    private ListNode first;
    private ListNode last;
    private Map<Integer, ListNode> cacheMap;
    private int capacity;
    private int count;

    public LRUCacheImplementByMapAndList(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<Integer, ListNode>();
        this.count = 0;
        this.first = new ListNode(-1, -1);
        this.last = new ListNode(-1, -1);
        first.next = last;
        last.prev = first;
    }

    public int get(int key) {
        if (cacheMap.containsKey(key)) {
            ListNode tmp = cacheMap.get(key);

            tmp.prev.next = tmp.next;
            tmp.next.prev = tmp.prev;

            tmp.next = first.next;
            first.next.prev = tmp;

            first.next = tmp;
            tmp.prev = first;
            return tmp.val;
        }

        return -1;
    }

    public void put(int key, int val) {
        if (cacheMap.containsKey(key)) {
            ListNode tmp = cacheMap.get(key);
            tmp.val = val;

            tmp.prev.next = tmp.next;
            tmp.next.prev = tmp.prev;

            tmp.next = first.next;
            first.next.prev = tmp;

            first.next = tmp;
            tmp.prev = first;
            return;
        }

        if (count >= capacity) {
            // 删除最后一个节点
            ListNode prev = last.prev;
            prev.prev.next = last;
            last.prev = prev.prev;

            prev.prev = null;
            prev.next = null;
            cacheMap.remove(prev.key);
        }


        ListNode node = new ListNode(key, val);
        cacheMap.put(key, node);
        node.next = first.next;
        first.next.prev = node;
        first.next = node;
        node.prev = first;
        if (++count > capacity) {
            count = capacity;
        }
    }

    public static void main(String[] args) {
        LRUCacheImplementByMapAndList cache = new LRUCacheImplementByMapAndList(2);

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
