package org.leetcode.p2196;

import org.leetcode.utils.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/create-binary-tree-from-descriptions/description/?envType=daily-question&envId=2024-07-15
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, List<int[]>> parent2Desc = new HashMap<>();
        for (int[] desc : descriptions) {
            parent2Desc.computeIfAbsent(desc[0], key -> new LinkedList<>()).add(desc);
        }
        Map<Integer, int[]> child2Desc = new HashMap<>();
        for (int[] desc : descriptions) {
            child2Desc.put(desc[1], desc);
        }
        int rootVal = findRoot(descriptions, child2Desc);
        return buildTree(rootVal, parent2Desc);
    }

    TreeNode buildTree(int val, Map<Integer, List<int[]>> parent2Desc) {
        TreeNode root = new TreeNode(val);
        List<int[]> children = parent2Desc.get(val);
        if (children == null) {
            return root;
        }
        for (int[] desc : children) {
            if (desc[2] == 0) {
                root.right = buildTree(desc[1], parent2Desc);
            } else {
                root.left = buildTree(desc[1], parent2Desc);
            }
        }
        return root;
    }

    int findRoot(int[][] descriptions, Map<Integer, int[]> child2Desc) {
        int parent = descriptions[0][0];
        while (child2Desc.containsKey(parent)) {
            parent = child2Desc.get(parent)[0];
        }
        return parent;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
