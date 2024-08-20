package org.leetcode.p3131;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/find-the-integer-added-to-array-i/
class Solution {
    public int addedInteger(int[] nums1, int[] nums2) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int n : nums1) {
            min1 = Math.min(min1, n);
        }
        for (int n : nums2) {
            min2 = Math.min(min2, n);
        }
        return min2 - min1;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{2, 6, 4}, new int[]{9, 7, 5}, 3);
        test(new int[]{10}, new int[]{5}, -5);
        test(new int[]{1, 1, 1, 1}, new int[]{1, 1, 1, 1}, 0);
    }

    private static void test(int[] nums1, int[] nums2, int expect) {
        Solution solution = new Solution();
        int ans = solution.addedInteger(nums1, nums2);
        String desc = String.format("added integer nums1=%s, nums2=%s", Arrays.toString(nums1), Arrays.toString(nums2));
        LeetCodeUtils.test(desc, ans, expect);
    }
}
