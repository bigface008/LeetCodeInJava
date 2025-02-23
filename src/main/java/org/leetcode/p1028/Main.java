package org.leetcode.p1028;

import org.leetcode.utils.TreeNode;

import java.util.*;

// https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/?envType=daily-question&envId=2025-02-22
class Solution {
    public TreeNode recoverFromPreorder(String traversal) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        final int N = traversal.length();
        int i = 0;
        while (i < N) {
            int depth = 0;
            while (i < N && traversal.charAt(i) == '-') {
                depth++;
                i++;
            }
            int val = 0;
            while (i < N && traversal.charAt(i) != '-') {
                val = val * 10 + (traversal.charAt(i) - '0');
                i++;
            }
            TreeNode node = new TreeNode(val);
            if (stack.isEmpty()) {
                stack.add(node);
                continue;
            }
            while (stack.size() > depth) {
                stack.pollLast();
            }
            TreeNode last = stack.getLast();
            if (last.left == null) {
                last.left = node;
            } else {
                last.right = node;
            }
            stack.add(node);
        }
        return stack.getFirst();
    }
}

class Solution2 {
    String traversal;
    List<int[]> nodes = new ArrayList<>();
    Map<Integer, List<Integer>> depth2Indices = new HashMap<>();

    public TreeNode recoverFromPreorder(String traversal) {
        this.traversal = traversal;
        final int N = traversal.length();
        int i = 0;
        while (i < N) {
            int dashStart = i;
            while (i < N && traversal.charAt(i) == '-') {
                i++;
            }
            int dashCnt = i - dashStart;
            int numStart = i;
            while (i < N && Character.isDigit(traversal.charAt(i))) {
                i++;
            }
            int val = Integer.valueOf(traversal.substring(numStart, i));
            int[] info = new int[]{dashCnt, val};
            nodes.add(info);
            depth2Indices.computeIfAbsent(dashCnt, k -> new ArrayList<>()).add(nodes.size() - 1);
        }
        return dfs(0, nodes.size() - 1, 0);
    }

    TreeNode dfs(int start, int end, int depth) {
        if (start > end) {
            return null;
        }
        int val = nodes.get(start)[1];
        TreeNode node = new TreeNode(val);
        if (start == end) {
            return node;
        }
        int newDepth = depth + 1;
        List<Integer> indices = depth2Indices.get(newDepth);
        if (indices == null || indices.isEmpty()) {
            return node;
        }
        int leftIdx = Collections.binarySearch(indices, start);
        if (leftIdx < 0) {
            leftIdx = -leftIdx - 1;
        }
        int rightNodePos = -1;
        if (leftIdx + 1 < indices.size() && indices.get(leftIdx + 1) <= end) {
            rightNodePos = indices.get(leftIdx + 1);
            node.right = dfs(rightNodePos, end, newDepth);
        }
        if (leftIdx < indices.size() && indices.get(leftIdx) <= end) {
            int leftNodePos = indices.get(leftIdx);
            if (rightNodePos == -1) {
                node.left = dfs(leftNodePos, end, newDepth);
            } else {
                node.left = dfs(leftNodePos, rightNodePos - 1, newDepth);
            }
        }
        return node;
    }
}


//class Solution2 {
//    char[] str;
//    String traversal;
//
//    public TreeNode recoverFromPreorder(String traversal) {
//        str = traversal.toCharArray();
//        this.traversal = traversal;
//        return dfs(0, str.length - 1, 0);
//    }
//
//    TreeNode dfs(int start, int end, int depth) {
////        int currDepth = depth + 1;
//        int i = start;
//        while (i <= end && Character.isDigit(traversal.charAt(i))) {
//            i++;
//        }
//        int val = Integer.valueOf(traversal.substring(start, i));
//        TreeNode node = new TreeNode(val);
//        int newDepth = depth + 1;
//        int leftNodeStart = i + newDepth; // TODO
//        i = leftNodeStart;
//        while (i <= end && Character.isDigit(traversal.charAt(i))) {
//            i++;
//        }
////        for (int i = currDepth - 1; i <= end; i++) {
////            for (int j = i - currDepth + 1; j <= i; j++) {
////                if
////            }
////        }
//    }
//}

public class Main {
    public static void main(String[] args) {
        {
            TreeNode root = new Solution().recoverFromPreorder("1-2--3--4-5--6--7");
            System.out.println(root.val);
        }
//        {
//            try {
//                TreeNode root = new Solution().recoverFromPreorder("1-2--3---4-5--6---7");
//                System.out.println(root.val);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        {
//            try {
//                TreeNode root = new Solution().recoverFromPreorder("1-401--349---90--88");
//                System.out.println(root.val);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        {
//            try {
//                TreeNode root = new Solution().recoverFromPreorder("22-85--28---41--93---27----18-----29----98-----19-12--100---33--43");
//                System.out.println(root.val);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

//    private static  test(String )
}
