package TargetOffer;

import java.util.LinkedList;

/**
 *
 * Offer52: 两个链表的第一个公共节点
 *
 * @author Richa
 * @date 2022/4/10 9:31
 */
public class FindFirstCommonNode {
    /**
     *  遍历得出较长的链表，较长的链表先遍历
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = 0, lenB = 0;
        ListNode p = headA;
        while (p.next != null) {
            lenA++;
            p = p.next;
        }
        p = headB;
        while (p.next != null) {
            lenB++;
            p = p.next;
        }

        ListNode q = null;
        int diff = 0;
        if (lenA > lenB) {
            diff = lenA - lenB;
            p = headA;
            q = headB;
        } else {
            diff = lenB - lenA;
            p = headB;
            q = headA;
        }

        for (int i = 0; i < diff; i++) {
            p = p.next;
        }

        while (p != null && q != null && p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

    /**
     * 使用栈从末尾向前找公共节点
     */
    public ListNode getInterNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        LinkedList<ListNode> stack1 = new LinkedList<>();
        LinkedList<ListNode> stack2 = new LinkedList<>();
        putInStack(headA, stack1);
        putInStack(headB, stack2);

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            ListNode interNode = stack1.peek();
            ListNode node1 = stack1.pop();
            ListNode node2 = stack2.pop();
            if (node1 == node2) {
                if (stack1.peek() != stack2.peek() || (stack1.isEmpty() || stack2.isEmpty())) {
                    return interNode;
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    private void putInStack(ListNode head, LinkedList<ListNode> stack) {
        ListNode node = head;
        while (node.next != null) {
            stack.push(node);
            node = node.next;
        }
    }
}
