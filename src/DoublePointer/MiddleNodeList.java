package DoublePointer;


import testCase.ListNode;

/**
 *
 * LeetCode 876: 链表的中间节点
 * 快慢指针
 * 偶数节点返回中间节点决定了判断条件，第一个和第二个不同
 * 
 * @author Richa
 * @date 2020/8/31 22:36
 */
public class MiddleNodeList {
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 返回第二个中间节点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 返回第一个中间节点
//        while (fast.next != null && fast.next.next != null) {
//            slow = slow.next;
//            fast = fast.next;
//        }
        return slow;
    }
}
