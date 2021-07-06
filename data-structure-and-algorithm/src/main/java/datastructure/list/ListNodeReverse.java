package datastructure.list;

/**
 * @program: data-structure-and-algorithm
 * 单链表反转
 * @author: Yejiaxin
 * @create: 2020-08-05 13:38
 */
public class ListNodeReverse {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return  dummy.next;
    }
}
