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
}
