package org.leetcode.p0513;

import org.leetcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

// https://leetcode.com/problems/find-bottom-left-tree-value/description/
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int ans = 0;
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            if (node.right != null) {
                deque.offer(node.right);
            }
            if (node.left != null) {
                deque.offer(node.left);
            }
            ans = node.val;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do something!");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.printf("Read line: %s", line);
        }
    }
}
