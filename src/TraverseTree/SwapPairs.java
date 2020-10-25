package TraverseTree;

import testCase.ListNode;

/**
 *
 * LeetCode 24: 两两交换链表中的节点
 *
 * @author Richa
 * @date 2020/10/25 23:02
 */
public class SwapPairs {
    // 递归
    public ListNode swapPairs(ListNode head) {
        // 链表中没有节点或者一个节点，不交换
        if (head == null || head.next == null) {
            return head;
        }
        // 新链表的头节点是原头节点的next
        ListNode newHead = head.next;
        // 递归交换两个节点后的链表
        head.next = swapPairs(newHead.next);
        // 交换head，newHead两个节点
        newHead.next = head;
        return newHead;
    }

    /**
     * 循环
     * 交换之前的节点关系是 temp -> node1 -> node2，交换之后的节点关系要变成 temp -> node2 -> node1
     */
    public ListNode swap(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        // temp 的后面没有节点或者只有一个节点，则没有更多的节点需要交换，因此结束交换
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            // 交换node1，node2
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }
}
