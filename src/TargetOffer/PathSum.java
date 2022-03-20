package TargetOffer;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Offer34: 二叉树中和为某一值的路径
 * 先序遍历+回溯法
 * 使用一个栈对路径进行保存，完成回溯
 * @author Richa
 * @date 2022/3/20 12:23
 */
public class PathSum {
    LinkedList<List<Integer>> ans = new LinkedList<>();
    /**
     * 保存路径的栈
     */
    LinkedList<Integer> pathStack = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return ans;
        }
        recur(root, sum);
        return ans;
    }
    private void recur(TreeNode node, int target) {
        if (node == null) {
            return;
        }
        pathStack.addLast(node.val);
        target = target - node.val;
        if (target == 0 && node.left == null && node.right == null) {
            ans.add(new LinkedList(pathStack));
        }
        recur(node.left, target);
        recur(node.right, target);
        // 回溯，回溯到上一层，target自然加node.val
        pathStack.removeLast();
    }
}
