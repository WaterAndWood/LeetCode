package TargetOffer;

import testCase.ListNode;

/**
 *
 * Offer24: 反转链表
 *
 * @author Richa
 * @date 2022/3/6 21:38
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 递归实现反转列表
     * @param head
     * @return 返回反转后头节点
     */
    public ListNode reverseListRecursion(ListNode head) {
        if (head == null) {
            return head;
        }

        return reverseList(head, null);
    }

    private ListNode reverseList(ListNode cur, ListNode prev) {
        if (cur == null) {
            return prev;
        }
        // 递归后继节点，一直不变，是原来的尾节点
        ListNode res = reverseList(cur.next, cur);
        // 修改引用，反转链表
        cur.next = prev;
        return res;
    }
}
