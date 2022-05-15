package SortAlgorithm;

import testCase.ListNode;

/**
 *
 * LeetCode147:对链表进行插入排序
 * 因为是单链表，所以内循环无法从已排序的尾部向前寻找插入点
 * @author Richa
 * @date 2022/5/15 14:05
 */
public class InsertListNodeSort {
    public ListNode insertSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        /**
         * lastSorted是已排序部分的最后节点，cur是待插入节点
         */
        ListNode lastSorted = head, cur = head.next;
        while (cur != null) {
            if (lastSorted.val <= cur.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= cur.val) {
                    prev = prev.next;
                }
                /**
                 * 摘出cur
                 */
                lastSorted.next = cur.next;
                /**
                 * 因为prev.next > cur，所以cur取代原来的prev.next，将cur查到prev.next之前
                 * cur插入到lastSorted之前，所以lastSorted不变
                 */
                cur.next = prev.next;
                prev.next = cur;
            }
            // lastSorted不变，需要插入排序的是lastSorted的后面的节点
            cur = lastSorted.next;
        }
        return dummyHead.next;
    }
}
