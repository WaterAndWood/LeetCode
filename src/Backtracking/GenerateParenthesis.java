package Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * LeetCode22: 括号生成
 *
 * @author Richa
 * @date 2022/5/5 22:31
 */
public class GenerateParenthesis {
    /**
     * 深度优先遍历（回溯算法）
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        StringBuilder path = new StringBuilder();
        dfs(path, n, n, res);
        return res;
    }

    /**
     *
     * @param path 从根节点到任意节点的路径，全程只使用一个，所以需要回溯。如果使用字符串，每次都新建，就可以不用回溯
     * @param left 左括号还有几个可以使用
     * @param right 右括号还有几个可以使用
     */
    private void dfs(StringBuilder path, int left, int right, List<String> res) {
        /**
         * 左右括号都有大于0个可以使用的才会哟=有分支
         * 左右括号剩余都是0结算
         */
        if (left == 0 && right == 0) {
            res.add(path.toString());
            return;
        }
        /**
         * 左边剩下的括号多，结果无效。剪枝
         */
        if (left > right) {
            return;
        }

        if (left > 0) {
            path.append("(");
            dfs(path, left - 1, right, res);
            path.deleteCharAt(path.length() - 1);
        }

        if (right > 0) {
            path.append(")");
            dfs(path, left, right - 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }


    class Node {
        private String res;
        /**
         * 剩余左括号
         */
        private int left;
        /**
         * 剩余有括号
         */
        private int right;

        public Node(String s, int left, int right) {
            this.res = s;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 深度优先搜素可以直接使用系统栈，在递归方法执行完成后，系统栈就把我们所需要的状态信息弹出，不需要自己编写节点类和显示使用栈
     * 广度优先搜索需要自己编写节点类和使用队列
     */
    public List<String> generateParenthesisBfs(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {
            /**
             * 和深度优先相同的处理逻辑
             */
            Node node = queue.poll();
            if (node.left == 0 && node.right == 0) {
                res.add(node.res);
            }
            if (node.left > 0) {
                queue.offer(new Node(node.res + "(", node.left - 1, node.right));
            }
            if (node.right > 0 && node.left < node.right) {
                queue.offer(new Node(node.res + ")", node.left, node.right - 1));
            }
        }
        return res;
    }
}
