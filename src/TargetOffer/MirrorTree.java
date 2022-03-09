package TargetOffer;

import testCase.TreeNode;

import java.util.LinkedList;

/**
 *
 * Offer27: 二叉树的镜像
 * 先序遍历这个树的每个节点，如果节点有子节点，交换两个子节点；当
 * 交换完所有非叶子节点的左右子节点，得到镜像
 *
 * @author Richa
 * @date 2022/3/9 22:40
 */
public class MirrorTree {
    public TreeNode mirrorTreeRecursion(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = mirrorTreeRecursion(root.right);
        root.right = mirrorTreeRecursion(temp);
        return root;
    }

    public TreeNode mirrorTreeLoop(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 叶子节点只会弹出，交换左右null
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return root;
    }
}
