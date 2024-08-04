package org.leetcode.p0098;

import org.leetcode.utils.TreeNode;

// https://leetcode.com/problems/validate-binary-search-tree/description/
class Solution {
    public boolean isValidBST(TreeNode root) {
        return inRange(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean inRange(TreeNode root, long left, long right) {
        if (root == null) {
            return true;
        }
        if (root.val >= right || root.val <= left) {
            return false;
        }
        return inRange(root.left, left, root.val) && inRange(root.right, root.val, right);
    }
}

public class Main {
    public static void main(String[] args) {
        {
            TreeNode root = new TreeNode(2147483647);
            Solution solution = new Solution();
            System.out.println(solution.isValidBST(root));
        }
    }
}
