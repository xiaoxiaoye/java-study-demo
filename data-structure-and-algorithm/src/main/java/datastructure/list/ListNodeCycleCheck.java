package datastructure.list;

/**
 * @program: data-structure-and-algorithm
 * 检测单向链表中是否存在环
 * 解题思路：
 *      通过两个快慢指针， 如果有环， 快指针通过环，会倒过来追上慢指针
 * @author: Yejiaxin
 * @create: 2020-08-05 13:40
 */
public class ListNodeCycleCheck {
    public boolean hasCycle(ListNode head) {
        ListNode fastNode = head;
        ListNode slowNode = head;

        while (fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if (slowNode == fastNode) {
                return  true;
            }
        }
        return false;
    }
}
