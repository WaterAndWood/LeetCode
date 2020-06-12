package TraverseTree;

import testCase.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * 从二叉树前序遍历和中序遍历重建二叉树
 *
 * @author Richa
 * @date 2020/6/12 11:18
 */
public class BuildTree {
    /* 存储中序遍历的值和对应的索引,空间换时间 */
    private Map<Integer, Integer> map;
    private int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inOrder) {
        if (preorder == null || inOrder == null || preorder.length != inOrder.length) {
            return null;
        }
        int len = preorder.length;
        this.preorder = preorder;
        map = new HashMap<Integer, Integer>(len);
        for (int i = 0; i < len; i++) {
            map.put(inOrder[i], i);
        }
        return buildTree(0, len - 1, 0, len - 1);
    }

    /**
     *
     * 递归创建子树，以根节点为分界
     *
     * @author Richa on 2020/6/12 14:09
     * @param preL 前序遍历数组的区间左端点
     * @param preR 前序遍历数组的区间右端点
     * @param inL 中序遍历数组的区间左端点
     * @param inR 中序遍历数组的区间右端点
     * @return TreeNode
     * @throws
     */
    public TreeNode buildTree(int preL, int preR, int inL, int inR) {
        if (preL > preR || inL > inR) {
            return null;
        }
        int pivot = preorder[preL];
        TreeNode root = new TreeNode(pivot);
        int pivotIndex = map.get(pivot);

        root.left = buildTree(preL + 1, preL + (pivotIndex - inL), inL, pivotIndex - 1);
        root.right = buildTree(preL + (pivotIndex - inL) + 1, preR, pivotIndex + 1, inR);
        return root;
    }

    public TreeNode buildTreeInLoop(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        TreeNode root = new TreeNode((preorder[0]));
        int length = preorder.length;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                /* 寻找前序遍历的左子树区间末尾 */
                stack.push(node.left);
            } else {
                while (!stack.empty() && stack.peek().val == inorder[inorderIndex]) {
                    // node是中序遍历的根节点
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] pre = {3, 9, 8, 5, 4, 10, 20, 15, 7};
        int[] in = {4, 5, 8, 10, 9, 3, 15, 20, 7};
        BuildTree buildTree = new BuildTree();
        TreeNode root = buildTree.buildTree(pre, in);
        System.out.println(root.val);
    }
}
