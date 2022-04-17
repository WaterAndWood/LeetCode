package TargetOffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Offer68: 二叉树的最近公共祖先
 * 二叉树没有排序，判断条件变多
 *
 * @author Richa
 * @date 2022/4/17 11:35
 */
public class LowestCommonAncestor2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * 使用两个链表保存到两个节点的路径，问题转变为两个链表的最后公共节点
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();
        getPath(root, p, pathP);
        getPath(root, q, pathQ);
        TreeNode res = null;
        if (!pathP.isEmpty() && !pathQ.isEmpty()) {
            Set<TreeNode> setP = new HashSet<>(pathP);
            for (TreeNode node : pathQ) {
                if (setP.contains(node)) {
                    res = node;
                }
            }
        }
        return res;
    }

    /**
     * 不属于path的节点要删除
     */
    private boolean getPath(TreeNode root, TreeNode target, List<TreeNode> list) {
        if (root == null) {
            return false;
        }
        list.add(root);
        if (root == target) {
            return true;
        }
        boolean inLeft = false, inRight = false;

        if (root.left != null) {
            inLeft = getPath(root.left, target, list);
        }
        if (!inLeft && root.right != null) {
            inRight = getPath(root.right, target, list);
        }
        // 既不是root，又不在左右子树
        if (!inLeft && !inRight) {
            list.remove(list.size() - 1);
        }
        return inLeft || inRight;
    }
}
