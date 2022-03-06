package TargetOffer;

/**
 *
 * Offer22: 链表中环的入口
 * 双指针
 * @author Richa
 * @date 2022/3/6 20:57
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;

        while (true) {
            // 因为fast走两步，所以判断fast.next == null
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            // 存在环
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
