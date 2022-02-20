package TargetOffer;

/**
 *
 * Offer8: 二叉树的下一个节点
 *
 * @author Richa
 * @date 2022/2/20 18:10
 */
public class GetNextTreeNode {
    public BinaryTreeNode getNext(BinaryTreeNode head) {
        if (head == null) {
            return null;
        }

        BinaryTreeNode next = null;

        /**
         * 如果一个节点有右子树，下一个节点是他右子树中最左子节点
         */
        if (head.right != null) {
            BinaryTreeNode right = head.right;
            while (right.left != null) {
                right = right.left;
            }
            next = right;
        } else if (head.parent != null) {
            /**
             * 一个节点没有右子树，如果节点是它父节点的左子节点，它的下一个节点就是父节点
             */
            if (head == head.parent.left) {
                next = head.parent;
            } else {
                /**
                 * 一个节点没有右子树，而且是它父节点的右子节点，沿着父节点向上遍历，直到找到
                 * 一个是它父节点的左子节点的节点。如果节点存在，那么这个节点的父节点就是要找的下一个节点
                 */
                BinaryTreeNode current = head;
                BinaryTreeNode parent = head.parent;
                while (parent != null || parent.right == current) {
                    current = parent;
                    parent = current.parent;
                }
                if (current == parent.left) {
                    next = parent;
                }
            }

        }
        return next;
    }
}

/**
 * 除了指向左右两个子节点，还有指向父节点
 */
class BinaryTreeNode {
    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode parent;
    BinaryTreeNode(int x) {
        val = x;
    }
}