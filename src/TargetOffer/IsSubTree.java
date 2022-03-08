package TargetOffer;

/**
 *
 * Offer26: 树的子结构
 * 递归遍历两个树
 *
 * @author Richa
 * @date 2022/3/8 22:48
 */
public class IsSubTree {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return isSubTree(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSubTree(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        // 结构一致的判断条件
        return (a.val == b.val) && isSubTree(a.left, b.left) && isSubTree(a.right, b.right);
    }

    public boolean isSubStructure1(TreeNode A, TreeNode B) {
        if (A != null && B != null) {
            return isSub(A, B) || isSubStructure1(A.left, B) || isSubStructure1(A.right, B);
        }
        return false;
    }

    private boolean isSub(TreeNode a, TreeNode b) {
       if (b == null) {
           return true;
       }
       if (a == null) {
           return false;
       }
       if (a.val != b.val) {
           return false;
       }
       return isSub(a.left, b.left) && isSub(a.right, b.right);
    }
}
