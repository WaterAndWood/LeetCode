package Leetcode;

import testCase.ListNode;

/**
 *
 * LeetCode21: 合并两个列表
 *
 * @author Richa
 * @date 2022/5/5 21:33
 */
public class MergeTwoList {
    public ListNode mergeTwoList(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }
        ListNode dummyNode = new ListNode(0), p = dummyNode;
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    p.next = list1;
                    list1 = list1.next;
                } else {
                    p.next = list2;
                    list2 = list2.next;
                }
            } else if (list1 != null) {
                p.next = list1;
                list1 = list1.next;
            } else if (list2 != null) {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        return dummyNode.next;
    }
}
