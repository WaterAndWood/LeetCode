package TargetOffer;

import java.util.*;

/**
 *
 * Offer37: 序列化和反序列化二叉树（Leetcode)
 * 按层序列化和反序列化
 * 以序列化的方式得到的字符串可以通过反序列化得到二叉树，具体方式自定
 *
 * @author Richa
 * @date 2022/3/24 23:30
 */
public class Codec {
    /**
     * BFS
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                res.append(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                res.append("null");
            }
            res.append(",");
        }

        return res.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] dataList = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(dataList[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty()) {
           TreeNode node = queue.poll();
           if (!"null".equals(dataList[i])) {
               node.left = new TreeNode(Integer.parseInt(dataList[i]));
               queue.offer(node.left);
           }
           i++;
           if (!"null".equals(dataList[i])) {
               node.right = new TreeNode(Integer.parseInt(dataList[i]));
               queue.offer(node.right);
           }
           i++;
        }
        return root;
    }

    /**
     * DFS：前序遍历
     */
    public String serializeDfs(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        dfs(res, root);
        return res.toString();
    }

    private void dfs(StringBuilder sb, TreeNode node) {
        if (node == null) {
            sb.append("null").append(",");
        } else {
            sb.append(node.val).append(",");
            dfs(sb, node.left);
            dfs(sb, node.right);
        }
    }

    public TreeNode deserializeDfs(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] str = data.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(str));
        return deserialize(list);
    }

    private TreeNode deserialize(List<String> list) {
        if ("null".equals(list.get(0))) {
            list.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        node.left = deserialize(list);
        node.right = deserialize(list);
        return node;
    }
}
