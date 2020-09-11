package DepthOrBreadthSearch;

import testCase.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * 剑指offer 36：二叉搜索树和双向链表
 * 中序遍历正好是排序的，方法有深度搜索和广度搜索，类似树的遍历
 *
 * @author Richa
 * @date 2020/9/7 23:12
 */
public class TreeToDequeList {
    /**
     * 递归中序遍历：
     * 终止条件：当前节点为空
     *
     * 递归左子树
     * 构建列表：当pre为空，访问头节点；当pre不为空，修改双向节点引用
     * 递归右子树
     *
     * 中序遍历结束，头结点和尾节点相连
     *
     */
    // 前驱节点和当前节点
    TreeNode pre, head;
    public TreeNode treeToDequeListDfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        // pre指向尾节点，执行pre = cur，并在dfs(cur.right)中终止递归
        pre.right = head;
        head.left = pre;
        return head;
    }
    private void dfs(TreeNode cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.left);
        // pre是双向链表中当前节点的前驱节点，pre == null，cur是双向链表的头节点
        if (pre == null) {
            head = cur;
        } else {
            // pre不是头节点，pre和cur双向连接
            pre.right = cur;
            cur.left = pre;

        }
        // pre指向当前的cur
        pre = cur;
        dfs(cur.right);
    }

    /**
     * 中序遍历循环实现，需要栈
     *
     */
    public TreeNode treeToDequeListLoop(TreeNode root) {
        if (root == null) {
            return null;
        }
        // listTail是树中序遍历的指针
        TreeNode listHead = null, listTail = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            // 树的中序遍历，左子节点入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // root是头节点，只确定链表头，否则双向连接listTail
            if (listHead == null) {
                listHead = root;
            } else {
                root.left = listTail;
                listTail.right = root;
            }
            listTail = root;
            root = root.right;
        }
        listTail.right = listHead;
        listHead.left = listTail;
        return listHead;
    }
}
