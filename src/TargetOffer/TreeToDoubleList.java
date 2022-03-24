package TargetOffer;


import java.util.LinkedList;

/**
 *
 * Offer36: 二叉搜索树与双向链表
 * 
 * @author Richa
 * @date 2022/3/23 23:33
 */
public class TreeToDoubleList {
    /**
     * 递归
     */
    TreeNode pre, head;
    public TreeNode treeToDoubleList(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    /**
     * 中序遍历
     */
    private void dfs(TreeNode cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.left);
        /**
         * 此时位于中序遍历的根
         * 遍历到最左叶子节点时，pre = null
         * pre表示的是cur的左侧节点
         */
        if (pre != null) {
            // pre和右侧cur建立连接
            pre.right = cur;
        } else {
            head = cur;
        }
        // cur和左侧pre建立连接
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }

    /**
     * 循环
     */
    public TreeNode treeNodeToDoubleList(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = null, listLastNode = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (head == null) {
                head = root;
            } else {
                listLastNode.right = root;
                root.left = listLastNode;
            }
            listLastNode = root;
            root = root.right;
        }
        listLastNode.right = head;
        head.left = listLastNode;
        return head;
    }
}
