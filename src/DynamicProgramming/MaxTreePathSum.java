package DynamicProgramming;

import testCase.TreeNode;

/**
 *
 * Leetcode124: 二叉树中的最大路径和
 *
 * @author Richa
 * @date 2022/5/26 15:23
 */
public class MaxTreePathSum {
    /**
     * 有动态规划的意思：
     * 对于当前节点有4个选择：
     * 1. 自己是一条路径
     * 2. 只跟左子节点合并成一条路径
     * 3. 只跟右子节点合并成一条路径
     * 4. 自己为桥梁，更左右子节点合并成一条路径
     * 第四种无法作为递归返回值
     */
    private int pathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return pathSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        int res = Math.max(node.val, node.val + Math.max(left, right));
        pathSum = Math.max(pathSum, Math.max(res, node.val + left + right));
        return res;
    }
}
