package DoublePointer;

import testCase.ListNode;

/**
 *
 * LeetCode: 回文链表
 *
 * @author Richa
 * @date 2022/6/25 17:26
 */
public class PalindromeList {
    public boolean isPalindromeList(ListNode head) {
        if (head == null) {
            return true;
        }

        ListNode midNode = findMidNode(head);
        ListNode halfNode = reverseList(midNode.next);

        ListNode p = head, q = halfNode;
        while (q != null) {
            if (p.val == q.val) {
                p = p.next;
                q = q.next;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 快慢指针找中间节点
     */
    private ListNode findMidNode(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 逆序链表而不是相互交换
     */
    private ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

}
