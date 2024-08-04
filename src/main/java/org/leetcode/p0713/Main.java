package org.leetcode.p0713;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/subarray-product-less-than-k/
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int left = 0;
        int ans = 0;
        int prod = 1;
        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            prod *= num;
            while (prod >= k) {
                prod /= nums[left];
                left++;
            }
            ans += right - left + 1;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }

    private static void test(int[] nums, int k, int expect) {
        Solution solution = new Solution();
        int output = solution.numSubarrayProductLessThanK(nums, k);
        String desc = String.format("num subarray product less than k nums=%s, k=%d", Arrays.toString(nums), k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
