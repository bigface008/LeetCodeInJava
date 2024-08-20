package org.leetcode.p0968;

import org.leetcode.utils.TreeNode;

// dfs(node)
// 0. select node
// 1. select parent
// 2. select child

// 0:
//   min(dfs(n.l, 0), dfs(n.l, 1), dfs(n.l, 2)) + min(dfs(n.r, 0), dfs(n.r, 1), dfs(n.r, 2))
// 1:
//   min(dfs(n.l, 0), dfs(n.l, 2)) + min(dfs(n.r, 0), dfs(n.r, 2))
// 2:
//   min(dfs(n.l, 0) + dfs(n.r, 0), (0,2), (2,0))

// https://leetcode.com/problems/binary-tree-cameras/
class Solution {
    public int minCameraCover(TreeNode root) {
        int[] res = dfs(root);
        return Math.min(res[0], res[2]);
    }

    int[] dfs(TreeNode node) { // 0, 1, 2
        if (node == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int c0 = Math.min(Math.min(left[0], left[1]), left[2]) + Math.min(Math.min(right[0], right[1]), right[2]) + 1;
        int c1 = Math.min(left[0], left[2]) + Math.min(right[0], right[2]);
        int c2 = Math.min(Math.min(left[0] + right[2], left[2] + right[0]), left[0] + right[0]);
        return new int[]{c0, c1, c2};
    }
}

public class Main {
}
