package TargetOffer;

import java.util.LinkedList;

/**
 *
 * Offer33: 二叉搜索树的后序遍历
 * 找到根节点，拆分为左子树和右子树，然后递归左子树和右子树
 *
 * @author Richa
 * @date 2022/3/20 10:47
 */
public class ValidPostOrder {
    public boolean validPostOrder(int[] postOrder) {
        if(postOrder == null || postOrder.length == 0) {
            return true;
        }
        return helper(postOrder, 0, postOrder.length - 1);
    }

    private boolean helper(int[] postOrder, int left, int right) {
        // left==right，说明只有一个节点，left > right，说明只有一个节点
        if (left >= right) {
            return true;
        }
        int mid = left;
        // 最右是根节点
        int root = postOrder[right];
        // 比根节点小的是左子树
        while (postOrder[mid] < root) {
            mid++;
        }
        // 左子树的根节点mid-1；右子树起始点mid
        int temp = mid;
        // 判断右子树是不是全部大于根节点
        while(temp < right) {
            if (postOrder[temp++] < root) {
                return false;
            }
        }
        // 递归左子树和右子树
        return helper(postOrder, left, mid - 1) && helper(postOrder, mid, right - 1);
    }

    /**
     * 使用栈解决：
     * 规律：后序遍历结果的逆序，挨着的两个数如果arr[i]<arr[i+1]，那么arr[i+1]一定是arr[i]的右子节点
     */
    public boolean validPostOrder2(int[] postOrder) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int parent = Integer.MAX_VALUE;
        for (int i = postOrder.length - 1; i >= 0; i--) {
            int cur = postOrder[i];
            // 当前元素cur是栈中某个元素的左节点
            while (!stack.isEmpty() && stack.peek() > cur) {
                parent = stack.pop();
            }
            if (cur > parent) {
                return false;
            }
            stack.push(cur);
        }
        return true;
    }
}
