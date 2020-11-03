package TraverseTree;

import testCase.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * LeetCode 95: 不同的搜索二叉树
 * root左子树小于root，root右子树大于root，以root分割1~n
 * 递归分割，缩短数组长度
 *
 * @author Richa
 * @date 2020/7/23 22:49
 */
public class BuildBinarySearchTree {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return generateTrees(1, n);
    }
    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();
        // 递归结束
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        // 枚举可行根节点i
        for (int i = start; i <= end; i++) {
            // 获得可行的左子树集合，左子树小于根节点i
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            // 获得可行的右子树集合，右子树大于根节点i
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            // 组合左右子树
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode curTree = new TreeNode(i);
                    curTree.left = left;
                    curTree.right = right;
                    allTrees.add(curTree);
                }
            }
        }
        return allTrees;
    }
}
