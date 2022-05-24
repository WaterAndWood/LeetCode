package DepthOrBreadthSearch;

import testCase.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * LeetCode99: 恢复二叉搜索树
 * 依靠二叉搜索树中序遍历有序的性质找出位置不对的TreeNode
 *
 * @author Richa
 * @date 2022/5/24 20:07
 */
public class RecoverSearchTree {
    /**
     * 使用额外的数组记录顺序
     */
    public void recoverTree1(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);
        TreeNode x = null, y = null;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).val > list.get(i + 1).val) {
                y = list.get(i + 1);
                if (x == null) {
                    x = list.get(i);
                }
            }
        }
        if (x != null && y != null) {
            int temp = x.val;
            x.val = y.val;
            y.val = temp;
        }
    }

    public void inOrder(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node);
        inOrder(node.right, list);
    }

    /**
     * 使用指针判断位置错误
     */
    private TreeNode x = null;
    private TreeNode y = null;
    private TreeNode pre = null;
    public void recoverTree2(TreeNode root) {
        dfs(root);
        if (x != null && y != null) {
            int temp = x.val;
            x.val = y.val;
            y.val = temp;
        }
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (pre == null) {
            pre = node;
        } else {
            if (pre.val > node.val) {
                y = node;
                if (x == null) {
                    x = node;
                }
            }
            pre = node;
        }
        dfs(node.right);
    }
}
