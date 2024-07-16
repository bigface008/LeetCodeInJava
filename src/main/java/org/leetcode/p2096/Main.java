package org.leetcode.p2096;

import org.leetcode.utils.TreeNode;

import java.security.IdentityScope;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another
class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        List<Character> path1 = new ArrayList<>();
        dfs(root, startValue, path1);
        List<Character> path2 = new ArrayList<>();
        dfs(root, destValue, path2);
        while (!path1.isEmpty() && !path2.isEmpty() && path1.get(0) == path2.get(0)) {
            path1.remove(0);
            path2.remove(0);
        }
        var builder = new StringBuilder();
        for (int i = 0; i < path1.size(); i++) {
            builder.append('U');
        }
        for (char ch : path2) {
            builder.append(ch);
        }
        return builder.toString();
    }

    boolean dfs(TreeNode node, int value, List<Character> path) {
        if (node == null) {
            return false;
        }
        if (node.val == value) {
            return true;
        }
        path.add('L');
        if (dfs(node.left, value, path)) {
            return true;
        }
        path.set(path.size() - 1, 'R');
        if (dfs(node.right, value, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
