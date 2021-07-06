package datastructure.list;

/**
 * @program: data-structure-and-algorithm
 * 底层存储利用数组来实现LRU缓存, 最新的节点放到数组尾部
 * @author: Yejiaxin
 * @create: 2020-08-04 15:46
 */
public class LRUCacheImplementByArray {
    class Pair {
        int key;
        int val;

        public Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // 利用数组存储
    private final Pair[] cache;
    // 容量
    private final int capacity;
    // 最后一个节点的位置
    private int position;

    public LRUCacheImplementByArray(int capacity) {
        this.capacity = capacity;
        this.cache = new Pair[capacity];
        this.position = -1;
    }

    public int get(int key) {
        for (int i = position; i >= 0; i--) {
            // 节点后的元素全部左移一位， 将节点添加到最后
            if (cache[i].key == key) {
                Pair tmp = cache[i];
                for (int j = i; j < position; j++) {
                    cache[j] = cache[j+1];
                }
                cache[position] = tmp;
                return tmp.val;
            }
        }
        return -1;
    }

    public void put(int key, int val) {
        // 节点后的元素全部左移一位， 将节点添加到最后, 并更新节点值
        for (int i = position; i >= 0; i--) {
            if (cache[i].key == key) {
                Pair tmp = cache[i];
                for (int j = i; j < position; j++) {
                    cache[j] = cache[j+1];
                }
                tmp.val = val;
                cache[position] = tmp;
                return;
            }
        }

        // 没找到节点，而且数据没有装满，将节点直接添加到最后
        if (position < capacity - 1) {
            cache[++position] = new Pair(key, val);
            return;
        }

        // 首节点元素删除，节点全部往前左移一位， 将元素添加到最后
        for (int i = 0; i < position; i++) {
            cache[i] = cache[i+1];
        }
        cache[position] = new Pair(key, val);
    }

    public static void main(String[] args) {
        LRUCacheImplementByArray cache = new LRUCacheImplementByArray(3);

        int value = -1;

//        cache.put(1, 1);
//        cache.put(2, 2);
//        value = cache.get(1);       // 返回  1
//        cache.put(3, 3);    // 该操作会使得关键字 2 作废
//        value = cache.get(2);       // 返回 -1 (未找到)
//        cache.put(4, 4);    // 该操作会使得关键字 1 作废
//        value = cache.get(1);       // 返回 -1 (未找到)
//        value = cache.get(3);       // 返回  3
//        value = cache.get(4);       // 返回  4

        /*
        ["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
        [[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]

        [null,null,null,null,null,4,3,2,-1,null,-1,2,3,-1,5]
         */
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        value = cache.get(4);
        value = cache.get(3);
        value = cache.get(2);
        value = cache.get(1);
        cache.put(5, 5);
        value = cache.get(1);
        value = cache.get(2);
        value = cache.get(3);
        value = cache.get(4);
        value = cache.get(5);
    }
}
