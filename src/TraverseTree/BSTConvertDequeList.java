package TraverseTree;

import testCase.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * LeetCode NewCoder ：剑指offer36
 * 二叉搜索树转化为双向链表
 * 递归和循环（栈）
 *
 * @author Richa
 * @date 2020/8/3 21:17
 */
public class BSTConvertDequeList {
    TreeNode pre, head;

    /**
     * 递归方法
     * pre 前驱节点，cur 当前节点
     */
    public TreeNode treeToDoublyList1(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        // 此时pre指向尾节点
        pre.right = head;
        head.left = pre;
        return head;
    }

    private void dfs(TreeNode cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.left);
        if (pre != null) {
            pre.right = cur;
        } else {
            head = cur;
        }
        cur.left = pre;
        // 最左节点赋值pre完成初始化
        pre = cur;
        dfs(cur.right);
    }

    /**
     * Stack使用Deque代替，Deque的实现可以是ArrayDeque和LinkedList
     */
    public TreeNode treeToDoublyList2(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode head = null;
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
           if (cur != null) {
               stack.push(cur);
               cur = cur.left;
           } else {
               cur = stack.pop();
               // 相当于中序遍历中对cur的操作
               if (pre != null) {
                   pre.right = cur;
                   cur.left = pre;
               } else {
                   head = cur;
               }
               pre = cur;
               cur = cur.right;
           }
        }
        // 首尾相接，形成循环双向列表
        pre.right = head;
        head.left = pre;
        return head;
    }
}
