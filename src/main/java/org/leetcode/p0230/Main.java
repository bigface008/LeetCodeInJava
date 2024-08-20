package org.leetcode.p0230;

import org.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

// Well the bst has a feature. We can traverse the tree in an inorder dfs way, and get an ascending list of all the values.
// So we can just use this feature. First make a list of integers, pass it to a recursive function named dfs.
// This function traverse the tree in a inorder way, and add the value of the given root node. We just need to pick the k-1.

// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root, k);
        return list.get(k - 1);
    }

    void dfs(List<Integer> list, TreeNode root, int k) {
        if (list.size() >= k) {
            return;
        }
        if (root == null) {
            return;
        }
        dfs(list, root.left, k);
        list.add(root.val);
        dfs(list, root.right, k);
    }
}

public class Main {
}
