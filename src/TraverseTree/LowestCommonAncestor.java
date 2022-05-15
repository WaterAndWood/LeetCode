package TraverseTree;

import testCase.TreeNode;

/**
 *
 * LeetCode 236：二叉树的最近公共祖先
 * 若root 是 p, q 的最近公共祖先，只能是以下情形：
 * 1. p, q 在root 的子树中，且分列root 的左右两侧
 * 2. p = root, 且q 在root 的左子树或者右子树
 * 3. q = root, 且p 在root 的左子树或者右子树
 *
 * 递归终止条件：越过叶节点返回null；当root 等于p, q 返回root
 * 递归左子节点，返回left；递归右子节点，返回right
 *
 * 返回值判断：
 * 1. 当left和right都为空，说明root的左子树和右子树都不含p, q，返回null
 * 2. 当left和right同时不为空，说明root的左子树或者右子树分别包含p, q，返回root
 * 3. 当left为空，right不为空，p, q都右子树，返回right
 * 4. 当right为空，left不为空，p, q都左子树，返回left
 * @author Richa
 * @date 2020/8/28 10:26
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        } else if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            // left != null && right != null
            return root;
        }
    }

    public TreeNode lowsetCommonAncestorSimple(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowsetCommonAncestorSimple(root.left, p, q);
        TreeNode right = lowsetCommonAncestorSimple(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

}
