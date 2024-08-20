package org.leetcode.p0337;

import org.leetcode.utils.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int rob = root.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{rob, notRob};
    }
}

class Solution3 {
    Map<TreeNode, int[]> memo;

    public int rob(TreeNode root) {
        memo = new HashMap<>();
        return Math.max(dfs(root, true), dfs(root, false));
    }

    int dfs(TreeNode root, boolean select) {
        if (root == null) {
            return 0;
        }
        int b = select ? 1 : 0;
        if (memo.containsKey(root) && memo.get(root)[b] != -1) {
            return memo.get(root)[b];
        }
        int res = 0;
        if (select) {
            res = root.val + dfs(root.left, false) + dfs(root.right, false);
        } else {
            res = Math.max(dfs(root.left, false), dfs(root.left, true))
                    + Math.max(dfs(root.right, false), dfs(root.right, true));
        }
        int[] tuple = new int[]{-1, -1};
        if (memo.containsKey(root)) {
            tuple = memo.get(root);
        }
        tuple[b] = res;
        return res;
    }
}

class Solution2 {
    Map<TreeNode, Integer> memo;

    public int rob(TreeNode root) {
        memo = new HashMap<>();
        return dfs(root);
    }

    int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        int rob = root.val;
        if (root.left != null) {
            rob += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            rob += rob(root.right.left) + rob(root.right.right);
        }
        int notRob = rob(root.left) + rob(root.right);
        int res = Math.max(rob, notRob);
        memo.put(root, res);
        return res;
    }
}

// dfs(root)
// if rob root:
//   root.val + dfs(root.left.left) + dfs(root.left.right) + dfs(root.right.left) + dfs(root.right.left)
// else:
//   dfs(root.left) + dfs(root.right)


public class Main {
    public static void main(String[] args) {

    }
}
