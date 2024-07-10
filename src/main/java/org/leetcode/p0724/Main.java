package org.leetcode.p0724;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/find-pivot-index/description/
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = sum - leftSum - nums[i];
            if (rightSum == leftSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 7, 3, 6, 5, 6}, 3);
        test(new int[]{1, 2, 3}, -1);
        test(new int[]{2, 1, -1}, 0);
    }

    private static void test(int[] nums, int expect) {
        Solution solution = new Solution();
        int output = solution.pivotIndex(nums);
        String desc = String.format("pivotIndex(%s) = %s, expect %s", Arrays.toString(nums), output, expect);
        LeetCodeUtils.test(desc, output, expect);
    }
}
