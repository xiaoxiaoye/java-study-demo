package datastructure.stack;


/**
 * @program: data-structure-and-algorithm
 * 基于链表实现的链式栈
 * @author: Yejiaxin
 * @create: 2020-08-05 17:44
 */
public class LinkedListStack {
    ListNode<String> dummyHead;
    int capacity;
    int count;

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
        this.dummyHead = new ListNode("");
        this.count = 0;
    }

    public boolean put(String item) {
        if (count == capacity) {
            return false;
        }

        ListNode<String> node = new ListNode<String>(item);
        node.next = dummyHead.next;
        dummyHead.next = node;
        count++;

        return true;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        ListNode<String> node = dummyHead.next;
        dummyHead.next = dummyHead.next.next;
        count--;
        return node.val;
    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack(2);
        System.out.println(stack.put("1"));
        System.out.println(stack.put("2"));
        System.out.println(stack.put("3"));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}
