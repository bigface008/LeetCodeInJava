package org.leetcode.p0110;

import org.leetcode.utils.TreeNode;

// https://leetcode.com/problems/balanced-binary-tree/
class Solution {
    public boolean isBalanced(TreeNode root) {
        return getDepth(root) != -1;
    }

    int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getDepth(node.left);
        if (left == -1) {
            return -1;
        }
        int right = getDepth(node.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}

public class Main {
}
