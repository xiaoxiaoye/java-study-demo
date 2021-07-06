package datastructure.list;

/**
 * @program: data-structure-and-algorithm
 * 两个有序链表合并， 对应leetcode 21题   https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * @author: Yejiaxin
 * @create: 2020-08-05 13:58
 */
public class SortedListMerge {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
                cur.next = null;
            } else {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
                cur.next = null;
            }
        }

        ListNode resume = l1 != null ? l1 : l2;
        cur.next = resume;
        return null;
    }
}
