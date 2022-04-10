package TargetOffer;

/**
 *
 * Offer54: 二叉搜索树的第K大节点
 *
 * @author Richa
 * @date 2022/4/10 15:28
 */
public class KthLargest {
    /**
     * 二叉搜索树的中序遍历为递增排序，但求的是第K大节点，需要倒序
     * 中序：左根右是递增，那么倒序：右根左是递减
     */
    private int ans = 0;
    private int k;
    public int kthLargest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.right);
        if (--k == 0) {
            ans = node.val;
            return;
        }
        dfs(node.left);
    }
}
