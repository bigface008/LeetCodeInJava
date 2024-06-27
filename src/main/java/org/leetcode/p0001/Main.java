package org.leetcode.p0001;

import org.leetcode.utils.LeetCodeUtils;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        return new int[]{};
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1});
        test(new int[]{3, 2, 4}, 6, new int[]{1, 2});
        test(new int[]{3, 3}, 6, new int[]{0, 1});
    }

    private static void test(int[] nums, int target, int[] expect) {
        Solution solution = new Solution();
        int[] output = solution.twoSum(nums, target);
        String desc = String.format("two sum nums=%s, target=%d", nums, target);
        LeetCodeUtils.test(desc, output, expect);
    }
}
