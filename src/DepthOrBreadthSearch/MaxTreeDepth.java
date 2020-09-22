package DepthOrBreadthSearch;

import testCase.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * 剑指offer：二叉树深度
 *
 * @author Richa
 * @date 2020/9/20 20:48
 */
public class MaxTreeDepth {
    /**
     * 深度优先搜索
     *
     */
    public int maxDepthDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepthDfs(root.left);
        int right = maxDepthDfs(root.right);

        return left > right ? left + 1 : right + 1;
    }

    /**
     * 广度优先搜索
     * 按层搜索，每一层相当于深度加1
     *
     */
    public int maxDepthBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            // 每一层加入队列的节点数
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
        }
        return res;
    }
}
