package org.leetcode.p0235;

import org.leetcode.utils.TreeNode;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int val = root.val;
        if (p.val < val && q.val < val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > val && q.val > val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}

public class Main {
}
