package TraverseTree;

import testCase.TreeNode;

import java.util.Stack;

/**
 *
 * 二叉树基本遍历
 *
 * @author Richa
 * @date 2020/6/9 0:16
 */
public class TraverseTree {
    /**
     *
     * 前序遍历：递归
     *
     * @author Richa on 2020/6/8 23:25
     * @param root 根节点
     * @return
     * @throws
     */
    public void preOrderTraverseRecursion(TreeNode root) {
        if (root != null) {
            System.out.print(root.val);
            System.out.print("->");
            preOrderTraverseRecursion(root.left);
            preOrderTraverseRecursion(root.right);
        }
    }

    /**
     *
     * 前序遍历：循环
     *
     * @author Richa on 2020/6/8 23:32
     * @param root 根节点
     * @return
     * @throws
     */
    public void preOrderTraverseLoop(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                System.out.print(node.val);
                System.out.print("->");
                stack.push(node);
                node = node.left;
            } else {
                TreeNode temp = stack.pop();
                node = temp.right;
            }
        }
    }

    /**
     *
     * 中序遍历：递归
     *
     * @author Richa on 2020/6/8 23:41
     * @param root 根节点
     * @return
     * @throws
     */
    public void inOrderTraverseRecursion(TreeNode root) {
        if (root != null) {
            inOrderTraverseRecursion(root.left);
            System.out.print(root.val);
            System.out.print("->");
            inOrderTraverseRecursion(root.right);
        }
    }

    /**
     *
     * 中序遍历：循环
     *
     * @author Richa on 2020/6/8 23:42
     * @param root 根节点
     * @return
     * @throws
     */
    public void inOrderTraverseLoop(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode temp = stack.pop();
                System.out.print(temp.val);
                System.out.print("->");
                node = temp.right;
            }
        }
    }

    /**
     *
     * 后序遍历：递归
     *
     * @author Richa on 2020/6/8 23:47
     * @param root 根节点
     * @return
     * @throws
     */
    public void lastOrderTraverseRecursive(TreeNode root) {
        if (root != null) {
            lastOrderTraverseRecursive(root.left);
            lastOrderTraverseRecursive(root.right);
            System.out.print(root.val);
            System.out.print("->");
        }
    }

    /**
     *
     * 后序遍历：循环
     *
     * @author Richa on 2020/6/9 0:04
     * @param root 根节点
     * @return
     * @throws
     */
    public void lastOrderTraverseLoop(TreeNode root) {
        TreeNode cur, pre = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            cur = stack.peek();
            // 当前节点为叶节点或者切换分支
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                System.out.print(cur.val);
                System.out.print("->");
                stack.pop();
                pre = cur;
            } else {
                // 栈中是逆序，先放右，后放左
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        TraverseTree traverseTree = new TraverseTree();
        traverseTree.lastOrderTraverseLoop(root);
        System.out.println("遍历结束");
    }
}
