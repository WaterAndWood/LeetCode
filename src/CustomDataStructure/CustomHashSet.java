package CustomDataStructure;

/**
 *
 * LeetCode: 705 简单实现哈希表
 * 桶的实现是二叉搜索树
 * 分层实现：bucketArray中利用hash值确定数组中桶的位置
 * 每个桶中进行二叉树的操作增删查
 *
 * @author Richa
 * @date 2020/7/25 9:41
 */
public class CustomHashSet {
    private Bucket[] bucketArray;
    private int keyRange;

    public CustomHashSet() {
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for (int i = 0; i < keyRange; i++) {
            this.bucketArray[i] = new Bucket();
        }
    }
    // 根据hash确定位于数组位置的索引
    protected int hash(int key) {
        return key % this.keyRange;
    }

    public void add(int key) {
        int bucketIndex = hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }

    public boolean contains(int key) {
        int bucketIndex = hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }
}

class Bucket {
    private BSTree tree;
    public Bucket() {
        this.tree = new BSTree();
    }

    public void insert(Integer key) {
        this.tree.root = this.tree.insertIntToBST(this.tree.root, key);
    }

    public void delete(Integer key) {
        this.tree.root = this.tree.deleteNode(this.tree.root, key);
    }

    public boolean exists(Integer key) {
        TreeNode node = this.tree.searchBSTree(this.tree.root, key);
        return node != null;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) {
        val = x;
    }
}

class BSTree {
    TreeNode root = null;
    /**
     * 二叉搜索树递归查找
     */
    public TreeNode searchBSTree(TreeNode root, int val) {
        if (root == null || val == root.val) {
            return root;
        }
        if (val > root.val) {
            return searchBSTree(root.right, val);
        } else {
            return searchBSTree(root.left, val);
        }
    }

    /**
     * 递归插入二叉树
     */
    public TreeNode insertIntToBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            root.right =  insertIntToBST(root.right, val);
        } else if (val == root.val) {
            return root;
        } else {
            root.left = insertIntToBST(root.left, val);
        }
        return root;
    }

    /**
     * 非叶节点的后继节点，代表的是二叉搜索树中序遍历序列的下一个节点。即比当前节点大的最小节点
     */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }
    /**
     * 非叶子节点的前驱节点，代表的是二叉搜索树中序遍历序列的前一个节点。即比当前节点小的最大节点
     */
    public int processor(TreeNode root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            // 叶子节点，直接删除
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = processor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

}
