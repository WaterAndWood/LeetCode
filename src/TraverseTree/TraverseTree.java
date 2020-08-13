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

    public void morrisPreOrder(TreeNode root) {
        TreeNode cur = root, pre = null;
        while (cur != null) {
            /**
             * 如果当前节点的left为空，输出，将其right作为当前节点
             * right能作为当前节点，是因为当前节点的right指向已经被改变，指向后继节点。
             * 返回当前节点的根节点
             */
            if (cur.left == null) {
                System.out.print(cur.val);
                System.out.print("->");
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                /**
                 * 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。
                 * pre指向的是pre的根节点，输出
                 * 当前节点更新为当前节点的左孩子
                 */
                if (pre.right == null) {
                    pre.right = cur;
                    System.out.print(cur.val);
                    System.out.print("->");
                    cur = cur.left;
                } else {
                    /**
                     * 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。当前节点更新为当前节点的右孩子。
                     *
                     */
                    pre.right = null;
                    cur = cur.right;
                }

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
     * pre是前驱节点，会改变节点right的指向
     *
     */
    public void morrisInOrder(TreeNode root) {
        TreeNode cur = root, pre = null;
        while (cur != null) {
            /**
             * 如果当前节点的left为空，输出，将其right作为当前节点
             * right能作为当前节点，是因为当前节点的right指向已经被改变，指向后继节点。
             * 后继节点在中序遍历中为当前节点的根（左根右）
             */
            if (cur.left == null) {
                System.out.print(cur.val);
                System.out.print("->");
                cur = cur.right;
            } else {
                /**
                 * 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
                 *
                 */
                pre = cur.left;
                /**
                 * 当前节点是root，它的前驱节点是左子树的最右节点，并改变左子树最右节点的right指向当前节点
                 *
                 */
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                /**
                 * 前驱节点right是当前节点，right设置为空，消除后加的指向关系，恢复树结构
                 * 输出当前节点，开始遍历右节点
                 */
                if (pre.right == cur) {
                    pre.right = null;
                    System.out.print(cur.val);
                    System.out.print("->");
                    cur = cur.right;
                }
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
//        traverseTree.preOrderTraverseRecursion(root);
        traverseTree.preOrderTraverseLoop(root);
        System.out.println();
        traverseTree.morrisPreOrder(root);
        System.out.println();
//        traverseTree.inOrderTraverseRecursion(root);
//        traverseTree.inOrderTraverseLoop(root);
//        traverseTree.lastOrderTraverseRecursive(root);
//        traverseTree.lastOrderTraverseLoop(root);
//        traverseTree.morrisInOrder(root);
        System.out.println("遍历结束");
    }
}
