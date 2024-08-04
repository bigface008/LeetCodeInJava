package org.leetcode.p0572;

import org.leetcode.utils.TreeNode;

// https://leetcode.cn/problems/subtree-of-another-tree/description/
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        } else {
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
    }

    boolean isSameTree(TreeNode root, TreeNode node) {
        if (root == null && node == null) {
            return true;
        }
        if (root == null || node == null) {
            return false;
        }
        if (root.val != node.val) {
            return false;
        }
        return isSameTree(root.left, node.left) && isSameTree(root.right, node.right);
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
