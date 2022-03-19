package TargetOffer;

import java.util.*;

/**
 *
 * Offer32: 从上到下打印二叉树
 *
 * @author Richa
 * @date 2022/3/19 17:37
 */
public class PrintTree {
    // 队列实现广度优先遍历
    public List<List<Integer>> levelOrderBfs(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            /**
             * 使用i初始化为队列长度，可以不用在while循环外定义变量记录queue初始长度
             */
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(temp);
        }
        return ans;
    }

    /**
     * 深度优先遍历，每层一个容器，将元素放入对应层的容器中
     */
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> levelOrderDfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        dfs(root, 1);
        return ans;
    }

    private void dfs(TreeNode node, int depth) {
        // 递归终止条件
        if (node == null) {
            return;
        }
        // 如果这个深度没有容器，就添加一个容器
        if (ans.size() < depth) {
            ans.add(new ArrayList<>());
        }

        ans.get(depth - 1).add(node.val);
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }

    /**
     * 分行从上到下打印二叉树
     * BFS + 记录下一层的节点数量
     */
    public void printInDefferentLine(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int nextLevelNum = 0;
        int toPrinted = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val);
            if (node.left != null) {
                queue.add(node.left);
                nextLevelNum++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevelNum++;
            }
            toPrinted--;
            // 该行打印结束，该换行
            if (toPrinted == 0) {
                System.out.print("\n");
                toPrinted = nextLevelNum;
                nextLevelNum = 0;
            }
        }
    }

    /**
     * 之字形打印二叉树
     * 剑指Offer定义两个栈。其实可以使用双端队列代替，分别从队头添加和队尾添加
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 模拟Deque
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                // temp 奇数层正序：添加至双端队列尾部；偶数层逆序：添加至双端队列头部
                if (ans.size() % 2 == 0) {
                    temp.addLast(node.val);
                } else {
                    temp.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(temp);
        }
        return ans;
    }

}
