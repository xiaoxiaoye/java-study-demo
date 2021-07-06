package datastructure.queue;

/**
 * @program: data-structure-and-algorithm
 * TODO
 * @author: Yejiaxin
 * @create: 2020-08-07 10:45
 */
public class LinkedListQueue {
    class ListNode {
        String val;
        ListNode next;
        public ListNode(String val) {
            this.val = val;
        }

    }

    private int count;
    private int capacity;
    // 用带头节点简化插入操作
    private ListNode dummyHead;
    private ListNode tail;

    public LinkedListQueue(int capacity) {
        this.capacity = capacity;

        this.count = 0;
        this.dummyHead = new ListNode(null);
        this.tail = this.dummyHead;
    }

    public boolean enqueue(String item) {
        if (count == capacity) {
            return false;
        }

        ListNode node = new ListNode(item);
        tail.next = node;
        tail = tail.next;
        count++;
        return true;
    }

    public String dequeue() {
        if (count > 0) {
            ListNode node = dummyHead.next;
            dummyHead.next = dummyHead.next.next;
            count--;
            return node.val;
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue(2);

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
