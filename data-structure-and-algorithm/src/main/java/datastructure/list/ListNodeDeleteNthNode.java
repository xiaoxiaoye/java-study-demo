package datastructure.list;

/**
 * @program: data-structure-and-algorithm
 * 删除单向链表中倒数第N个节点 leetcode 19, https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * @author: Yejiaxin
 * @create: 2020-08-05 14:24
 */
public class ListNodeDeleteNthNode {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode fast = dummyHead;
        ListNode slow = dummyHead;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
            if (fast == null) {
                return head;
            }
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyHead.next;
    }
}
