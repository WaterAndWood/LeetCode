package Leetcode;

import testCase.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * LeetCoode23: 合并k个有序链表
 * 时间复杂度计算：每个链表的最长长度是n，k个链表
 *
 * @author Richa
 * @date 2022/5/8 10:09
 */
public class MergeKLists {
    /**
     * k个指针指向k个链表，顺序合并
     * 时间复杂度：O(k^2*n)
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode dummyHead = new ListNode(0), tail = dummyHead;
        int k = lists.length;
        while(true) {
            ListNode minNode = null;
            int minPointer = -1;
            for (int i = 0; i < k; i++) {
                if (lists[i] == null) {
                    continue;
                }

                if (minNode == null || minNode.val > lists[i].val) {
                    minNode = lists[i];
                    minPointer = i;
                }
            }
            if (minPointer == -1) {
                break;
            }
            tail.next = minNode;
            tail = tail.next;
            // 修改链表
            lists[minPointer] = lists[minPointer].next;
        }
        return dummyHead.next;
    }

    /**
     * K 条链表的总结点数是 N，平均每条链表有 N/K 个节点
     * 使用小根堆
     * 时间复杂度：O(Nlogk) 或者O(kn*logk)
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> priorityQueue = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.offer(node);
            }
        }

        ListNode dummyHead = new ListNode(0), tail = dummyHead;
        while (!priorityQueue.isEmpty()) {
            ListNode minNode = priorityQueue.poll();
            tail.next = minNode;
            tail = tail.next;
            // 把minNode的下一个节点放入小根堆
            if (minNode.next != null) {
                priorityQueue.offer(minNode.next);
            }
        }
        return dummyHead.next;
    }

    /**
     * 给出合并两条链表的方法，遍历lists，依次合并两条链表
     * 时间复杂度：O(kn * k)
     */
    // 递归
    public ListNode merge2ListRecur(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge2ListRecur(l1.next, l2);
            return l1;
        }
        l2.next = merge2ListRecur(l1, l2.next);
        return l2;
    }

    public ListNode merge2ListLoop(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0), tail = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }

            tail = tail.next;
        }
        tail.next = l1 == null ? l2 : l1;
        return dummyHead.next;
    }

    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode res = null;

        for (ListNode node : lists) {
            res = merge2ListLoop(res, node);
        }
        return res;
    }

    public ListNode mergeKList4(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int lo, int hi) {
        if (lo == hi) {
            return lists[lo];
        }
        int mid = lo + (hi - lo) / 2;
        ListNode l1 = merge(lists, lo, mid);
        ListNode l2 = merge(lists, mid + 1, hi);
        return merge2ListLoop(l1, l2);
    }
}
