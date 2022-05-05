package DoublePointer;

import testCase.ListNode;

/**
 *
 * LeetNode 19: 删除链表的倒数第N个节点
 * 双指针：快慢指针
 *
 * @author Richa
 * @date 2022/5/5 19:13
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode fast = dummyNode;
        // 快指针先走n步
        for (int i = 0; i <= n; i++) {
            if (fast == null) {
                return head;
            }
            fast = fast.next;
        }
        ListNode slow = dummyNode;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummyNode.next;
    }
}
