package datastructure.list;

/**
 * @program: data-structure-and-algorithm
 * 判断链表是否为回文串, 时间复杂度为O(n), 空间复杂度为O(1)
 * 难点： 需要考虑中间节点的处理， 如果是偶数个节点， 刚好分成数据相等的两段， 如果是奇数个节点， 前后两段都需要把中间节点去掉
 * @author: Yejiaxin
 * @create: 2020-08-05 09:34
 */
public class PalindromeByList {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;

        ListNode reserved = new ListNode(-1);
        // 快慢指针找到中间节点, 并反转前半段节点
        while (fast != null && fast.next != null) {
            ListNode prev = slow;
            fast = fast.next.next;
            slow = slow.next;

            // 反转节点
            prev.next = reserved.next;
            reserved.next = prev;
        }

        // 偶数个节点， fast指针会遍历到最后一个节点之后， 以此来判断， 奇数个节点的时候，后半段的节点需要跳过中间节点
        ListNode unreserved = fast == null ? slow : slow.next;

        reserved = reserved.next;
        while (unreserved != null) {
            if (unreserved.val != reserved.val) {
                return false;
            }
            unreserved = unreserved.next;
            reserved = reserved.next;
        }

        return true;
    }

    public static void main(String[] args) {
        PalindromeByList s = new PalindromeByList();

        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(1);
        head.next = n1;

        boolean r1 = s.isPalindrome(head);
        System.out.println(r1);
    }
}
