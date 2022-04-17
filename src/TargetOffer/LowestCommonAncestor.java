package TargetOffer;

/**
 *
 * Offer68: 二叉搜索树的最近公共祖先
 * 在树中从上到下找到第一个在两个输入节点之间的节点是最低公共祖先
 *
 * @author Richa
 * @date 2022/4/17 11:14
 */
public class LowestCommonAncestor {
    /**
     * 迭代循环
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else {
                // root在p.val和q.val之间
                break;
            }
        }
        return root;
    }

    /**
     * 递归
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor2(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor2(root.left, p, q);
        }
        return root;
    }
}
