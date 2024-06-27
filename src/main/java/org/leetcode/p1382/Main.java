package org.leetcode.p1382;

import com.sun.source.tree.Tree;
import org.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer> values = new ArrayList<>();
        dfs(root, values);
        return buildTree(values, 0, values.size() - 1);
    }

    private void dfs(TreeNode node, ArrayList<Integer> values) {
        if (node == null) {
            return;
        }
        dfs(node.left, values);
        values.add(node.val);
        dfs(node.right, values);
    }

    private TreeNode buildTree(List<Integer> values, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(values.get(start));
        }
        int midIdx = (start + end) / 2;
        int val = values.get(midIdx);
        TreeNode node = new TreeNode(val);
        node.left = buildTree(values, start, midIdx - 1);
        node.right = buildTree(values, midIdx + 1, end);
        return node;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        {
            TreeNode root = new TreeNode(1);
            root.right = new TreeNode(2);
            root.right.right = new TreeNode(3);
            root.right.right.right = new TreeNode(4);
            TreeNode output = solution.balanceBST(root);
            System.out.println();
        }
        {
            TreeNode root = new TreeNode(2);
            root.left = new TreeNode(1);
            root.right = new TreeNode(3);
            TreeNode output = solution.balanceBST(root);
            System.out.println();
        }
    }

    private static void test() {

    }
}
