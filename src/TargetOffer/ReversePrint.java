package TargetOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Offer6: 从尾到头打印列表
 *
 * @author Richa
 * @date 2022/2/20 9:40
 */
public class ReversePrint {
    /**
     * 使用栈实现逆序，数组作为输出
     */
    public int[] reversePrint1(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        int[] ans = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            ans[i++] = stack.pop().value;
        }
        return ans;
    }

    /**
     * 递归实现逆序：递归本质就是堆栈，调用自身前会把当前的上下文压入栈中
     */
    public int[] reversePrint2(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        List<Integer> arr = new ArrayList<>();
        helper(head, arr);
        int[] ans = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            ans[i] = arr.get(i);
        }
        return ans;
    }

    private void helper(ListNode node, List<Integer> arr) {
        if (node == null) {
            return;
        }
        helper(node.next, arr);
        arr.add(node.value);
    }


    /**
     * 遍历原链表以生成一个的逆序的链表，逐个摘出原节点，组成逆序链表
     * 逆序需要知道前一个节点的引用，才能将cur.next = pre
     */
    public int[] reversePrint3(ListNode head) {
        if (head == null) {
            return new int[0];
        }

        int size = 0;
        // 逆序链表的头，初始化null相当于指向链表尾
        ListNode newHead = null;
        ListNode cur = head;
        while (cur != null) {
            // 指向后面的链表
            ListNode temp = cur.next;
            // 摘出cur，将newHead接在后面
            cur.next = newHead;
            // newHead向前移动一位，此时cur相当于newHead之前的节点
            newHead = cur;
            // cur指向后面的链表，开始逐个节点摘出
            cur = temp;
            size++;

        }

        int[] ans = new int[size];
        int i = 0;
        while (newHead != null) {
            ans[i++] = newHead.value;
            newHead = newHead.next;
        }
        return ans;
    }

}

class ListNode {
    int value;
    ListNode next;
    ListNode(int x) {
        value = x;
    }
}
