package TargetOffer;

import testCase.ListNode;

/**
 * @author wangzhen
 * @creatTime 2022/3/5 2:03 下午
 * @description Offer18: 删除链表中的节点
 */
public class DeleteNode {
    /**
     * 这里是值而不是节点
     * @param head 链表头节点
     * @param val 删除节点的值而不是节点，因此只能遍历链表，时间复杂度O(n)
     * @return 返回删除节点的链表的头
     */
    public ListNode deleteNode(ListNode head, int val) {
        /**
         * 在头节点前构造哑节点，新链表的头即dummy.next
         */
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        while (cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        /**
         * 删除节点
         */
        pre.next = cur.next;
        return dummy.next;
    }

    /**
     * 时间复杂度（O(1) * (n-1) + O(n)) / n = O(1)
     * 把下一个节点的内容复制到要删除的节点上，就算删除了
     * @param head
     * @param toBeDeleted 删除节点
     * @return 返回新链表的头节点
     */
    public ListNode deleteNode(ListNode head, ListNode toBeDeleted) {
        if (head == null || toBeDeleted == null) {
            return head;
        }
        // 待删除节点不是尾节点
        if (toBeDeleted.next != null) {
            ListNode deleteNext = toBeDeleted.next;
            toBeDeleted.val = deleteNext.val;
            toBeDeleted.next = deleteNext.next;
            deleteNext = null;
        } else if (head == toBeDeleted) {
            // 链表只有一个节点，删除头节点，也是尾节点
            head = null;
        } else {
            // 待删除是尾节点，找到待删除的前一个节点进行删除
            ListNode cur = head;
            while (cur.next != toBeDeleted) {
                cur = cur.next;
            }
            cur.next = null;
        }
        return head;
    }

    /**
     * 删除链表中重复节点
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = head, cur = head.next;
        while (cur != null) {
            if (pre.val == cur.val) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}
