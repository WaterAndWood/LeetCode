package CustomDataStructure;

import testCase.ListNode;

/**
 *
 * LeetCode 61:旋转链表
 * 旋转链表k个位置，相当于将链表后k%n个节点移动到链表的最前面，然后将链表后k%n个节点和前n-k个节点连接
 *
 * @author Richa
 * @date 2022/5/18 19:32
 */
public class RotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        int n = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            n++;
        }
        k %= n;

        ListNode p = head;
        for (int i = 0; i < n - k - 1; i++) {
            p = p.next;
        }
        tail.next = head;
        head = p.next;
        // 断开p和head连接
        p.next = null;
        return head;
    }
}
