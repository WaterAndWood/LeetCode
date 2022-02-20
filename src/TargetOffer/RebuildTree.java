package TargetOffer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Offer7：重建二叉树
 * 递归
 *
 * @author Richa
 * @date 2022/2/20 15:37
 */
public class RebuildTree {
    /**
     * 保存中序遍历的value和index，方便查找root的index，这样就不要每次遍历中序序列
     */
    private Map<Integer, Integer> valueIndexMap;
    /**
     * 中序遍历提供根的位置和左右子树区间范围
     */
    private int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        int preLen = preorder.length;
        int inLen = inorder.length;
        valueIndexMap = new HashMap<>(inLen);
        for (int i = 0; i < inLen; i++) {
            valueIndexMap.put(inorder[i], i);
        }
        this.preorder = preorder;
        return buildTree(0, preLen - 1, 0, inLen - 1);
    }

    /**
     * 前序遍历数组中左子树对应中序遍历中的左子树，前序遍历中的右子树对应中序遍历中的右子树，区间对应
     * 递归找出左子树和右子树
     * @param preL 前序遍历数组区间左端点
     * @param preR 前序遍历数组区间右端点
     * @param inL 中序遍历数组区间左端点
     * @param inR 中序遍历数组去加右端点
     * @return
     */
    private TreeNode buildTree(int preL, int preR, int inL, int inR) {
        if (preL > preR || inL > inR) {
            return null;
        }
        int pivot = preorder[preL];
        TreeNode root = new TreeNode(pivot);
        /**
         * 中序遍历中root的index
         */
        int pivotIndex = valueIndexMap.get(pivot);

        root.left = buildTree(preL + 1, pivotIndex - inL + preL, inL, pivotIndex - 1);
        root.right = buildTree(pivot - inL + preL + 1, preR, pivotIndex + 1, inR);
        return root;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

