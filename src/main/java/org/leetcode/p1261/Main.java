package org.leetcode.p1261;

import org.leetcode.utils.TreeNode;

import java.util.HashSet;
import java.util.Set;

class FindElements {
    TreeNode root;
    Set<Integer> values = new HashSet<>();

    public FindElements(TreeNode root) {
        this.root = root;
        if (root == null) {
            return;
        }
        root.val = 0;
        values.add(0);
        recover(root);
    }

    public boolean find(int target) {
        return values.contains(target);
    }

    void recover(TreeNode node) {
        if (node.left != null) {
            node.left.val = node.val * 2 + 1;
            values.add(node.left.val);
            recover(node.left);
        }
        if (node.right != null) {
            node.right.val = node.val * 2 + 2;
            values.add(node.right.val);
            recover(node.right);
        }
    }
}

public class Main {
}
