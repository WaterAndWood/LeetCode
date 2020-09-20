package DepthOrBreadthSearch;

import testCase.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * LeetCode 404: 左叶子之和
 * 左叶子是某个节点的左子节点并且是叶子节点
 * 
 * @author Richa
 * @date 2020/9/19 23:51
 */
public class SumOfLeftLeaves {
    /**
     * 深度优先搜索
     *
     */
    public int sumOfLeftLeavesDfs(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }

    public int dfs(TreeNode node) {
        int ans = 0;
        if (node.left != null) {
            ans += isLeafNode(node.left) ? node.left.val : dfs(node.left);
        }
        if (node.right != null && !isLeafNode(node.right)) {
            ans += dfs(node.right);
        }
        return ans;
    }

    // 判断是叶子节点
    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    /**
     * 广度优先搜索
     *
     */
    public int sumOfLeftLeavesBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ans = 0;

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp.left != null) {
                if (isLeafNode(temp.left)) {
                    ans += temp.left.val;
                } else {
                    queue.add(temp.left);
                }
            }
            if (temp.right != null) {
                if (!isLeafNode(temp.right)) {
                    queue.offer(temp.right);
                }
            }
        }
        return ans;
    }
}
