package org.leetcode.p0958;

import org.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        boolean reachNull = false;
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            if (node == null) {
                reachNull = true;
            } else {
                if (reachNull) {
                    return false;
                }
                deque.offer(node.left);
                deque.offer(node.right);
            }
        }
        return true;
    }
}

public class Main {
}
