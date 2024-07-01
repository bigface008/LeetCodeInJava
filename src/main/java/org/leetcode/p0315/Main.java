package org.leetcode.p0315;

import com.sun.source.tree.Tree;
import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/count-of-smaller-numbers-after-self/
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        TreeNode root = buildTree(nums, 0, nums.length - 1);
        return new ArrayList<>();
    }

    TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode();
        node.val = nums[mid];
        node.left = buildTree(nums, start, mid - 1);
        node.right = buildTree(nums, mid + 1, end);
        return node;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{5, 2, 6, 1}, "[2,1,1,0]");
        test(new int[]{-1}, "[0]");
        test(new int[]{-1, -1}, "[0,0]");
    }

    private static void test(int[] nums, String expectStr) {
        String desc = String.format("nums=%s", Arrays.toString(nums));
        List<Integer> expect = LeetCodeUtils.makeIntList(expectStr);
        Solution solution = new Solution();
        List<Integer> output = solution.countSmaller(nums);
        LeetCodeUtils.test(desc, output, expect);
    }
}
