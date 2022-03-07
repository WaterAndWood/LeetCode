package TargetOffer;

/**
 *
 * Offer25: 合并两个排序的链表
 *
 * @author Richa
 * @date 2022/3/7 23:06
 */
public class MergeListNode {

    /**
     * 循环合并
     */
    public ListNode mergeTwoListLoop(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode dummyNode = new ListNode(0);
        ListNode mergeHead = dummyNode;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                mergeHead.next = l2;
                break;
            } else if (l2 == null) {
                mergeHead.next = l1;
                break;
            } else {
                if (l1.value < l2.value) {
                    mergeHead.next = l1;
                    l1 = l1.next;
                } else {
                    mergeHead.next = l2;
                    l2 = l2.next;
                }
            }
            mergeHead = mergeHead.next;
        }
        return dummyNode.next;
    }

    /**
     * 递归实现合并
     */
    public ListNode mergeTwoListRecursion(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode mergeHead = null;

        if (l1.value < l2.value) {
            mergeHead = l1;
            mergeHead.next = mergeTwoListRecursion(l1.next, l2);
        } else {
            mergeHead = l2;
            mergeHead.next = mergeTwoListRecursion(l2.next, l1);
        }
        return mergeHead;
    }
}
