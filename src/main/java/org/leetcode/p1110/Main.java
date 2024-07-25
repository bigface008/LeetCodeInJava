package org.leetcode.p1110;

import com.sun.source.tree.Tree;
import org.leetcode.utils.TreeNode;

import java.util.*;

// https://leetcode.com/problems/delete-nodes-and-return-forest/description/?envType=daily-question&envId=2024-07-17
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> deleteSet = new HashSet<>();
        for (int num : to_delete) {
            deleteSet.add(num);
        }
        List<TreeNode> ans = dfs(root, deleteSet);
        return ans;
    }

    List<TreeNode> dfs(TreeNode root, Set<Integer> deleteSet) {
        List<TreeNode> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        var left = dfs(root.left, deleteSet);
        var right = dfs(root.right, deleteSet);
        if (deleteSet.contains(root.val)) {
            ans.addAll(left);
            ans.addAll(right);
        } else {
            ans.add(root);
            for (int i = 0; i < left.size(); i++) {
                if (left.get(i) == root.left) {
                    continue;
                }
                ans.add(left.get(i));
            }
            for (int i = 0; i < right.size(); i++) {
                if (right.get(i) == root.right) {
                    continue;
                }
                ans.add(right.get(i));
            }
        }
        if (root.left != null && deleteSet.contains(root.left.val)) {
            root.left = null;
        }
        if (root.right != null && deleteSet.contains(root.right.val)) {
            root.right = null;
        }
        return ans;
    }
}

class Solution2 {
    public TreeNode dfs(TreeNode root, List<TreeNode> res, Set<Integer> set) {
        if (root == null) return root;
        root.left = dfs(root.left, res, set);
        root.right = dfs(root.right, res, set);
        if (set.contains(root.val)) {
            if (root.left != null) {
                res.add(root.left);
            }
            if (root.right != null) {
                res.add(root.right);
            }
            return null;
        }
        return root;
    }

    public List<TreeNode> delNodes(TreeNode root, int[] del) {
        List<TreeNode> res = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        for (int x : del) set.add(x);
        dfs(root, res, set);
        if (!set.contains(root.val)) {
            res.add(root);
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        {
            var root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);
            root.right.left = new TreeNode(6);
            root.right.right = new TreeNode(7);
            var solution = new Solution2();
            var res = solution.delNodes(root, new int[]{3, 5});
            System.out.println(res);
        }
    }
}
