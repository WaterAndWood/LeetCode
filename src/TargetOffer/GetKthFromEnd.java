package TargetOffer;

import testCase.ListNode;

/**
 *
 * Offer22: 链表中倒数第k个节点
 * 双指针
 * 两个指针之间距离保持k-1，当第一个指针到达尾节点，第二个指针到达倒数k个节点
 *
 * @author Richa
 * @date 2022/3/6 18:26
 */
public class GetKthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode ahead = head;
        ListNode behind = null;

        for (int i = 0; i < k - 1; i++) {
            if (ahead.next != null) {
                ahead = ahead.next;
            } else {
                // 链表长度不到k
                return null;
            }
        }

        behind = head;

        while (ahead.next != null) {
            ahead = ahead.next;
            behind = behind.next;
        }
        return behind;
    }
}
