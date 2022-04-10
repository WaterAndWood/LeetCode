package TargetOffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * Offer55: 二叉树深度
 * 树的遍历：深度优先搜索（DFS），广度优先搜索（BFS）
 * DFS：前序、中序和后序遍历
 * BFS：层序遍历
 * 
 * @author Richa
 * @date 2022/4/10 17:42
 */
public class MaxDepth {
    /**
     * DFS往往利用递归或者栈实现
     */
    public int maxDepthDfs(TreeNode root) {
        if (root == null){
            return 0;
        }
        int nLeft = maxDepthDfs(root.left);
        int nRight = maxDepthDfs(root.right);
        return Math.max(nLeft, nRight) + 1;
    }

    /**
     * BFS通常利用队列实现
     * 每遍历一层，计数器+1
     */
    public int maxDepthBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            Queue<TreeNode> temp = new LinkedList<>();
            // 遍历完一层
            for (TreeNode node : queue) {
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            queue = temp;
            res++;
        }
        return res;
    }

    /**
     * 如果先序遍历从上向下递归，每个节点遍历多次，重复计算
     * 采用后序遍历从下向上，只遍历一遍
     * 当节点root左右子树深度差小于等于1，返回当前节点深度max(left, right) + 1；如果高度差大于2，代表此子树不是平衡数
     */
    public boolean isBalanced(TreeNode root) {
        // 先序遍历从上到下
        if (root == null) {
            return true;
        }
        return (Math.abs(depth(root.left) - depth(root.right)) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }
    // 某个节点为root的最大深度
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    /**
     * 后序遍历+剪枝，每个节点只遍历一次，不会重复计算，计算量小
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recur(root) != -1;
    }

    /**
     * 不是平衡树则返回-1，用来剪枝
     */
    private int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = recur(root.left);
        if (left == -1) {
            return -1;
        }
        int right = recur(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
