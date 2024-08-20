package org.leetcode.p0103;

import org.leetcode.utils.TreeNode;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        boolean forward = true;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.poll();
                if (node.left != null) {
                    deque.offer(node.left);
                }
                if (node.right != null) {
                    deque.offer(node.right);
                }
                level.add(node.val);
            }
            if (!forward) {
                Collections.reverse(level);
            }
            ans.add(level);
            forward = !forward;
        }
        return ans;
    }
}

public class Main {
}
