package TargetOffer;

import testCase.Node;

/**
 *
 * Offer 35: 复杂链表的复制
 * 
 * @author Richa
 * @date 2022/3/22 22:57
 */
public class CloneNode {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        // 遍历原链表拼接新链表
        while (cur != null) {
            Node joinNode = new Node(cur.val);
            joinNode.next = cur.next;
            cur.next = joinNode;
            cur = joinNode.next;
        }

        // 遍历链表，赋值新链表的random
        cur = head;
        while(cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 拆分链表
        Node pre = head, res = head.next;
        cur = head.next;
        while (cur.next != null) {
            pre.next = cur.next;
            cur.next = cur.next.next;

            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null;
        return res;
    }
}
