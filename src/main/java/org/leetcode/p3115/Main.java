package org.leetcode.p3115;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-prime-difference/description
class Solution {
    public int maximumPrimeDifference(int[] nums) {
        int minIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (isPrime(nums[i])) {
                minIdx = i;
                break;
            }
        }
        int maxIdx = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (isPrime(nums[i])) {
                maxIdx = i;
                break;
            }
        }
        return maxIdx - minIdx;
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{4, 2, 9, 5, 3}, 3);
        test(new int[]{4, 8, 2, 8}, 0);
    }

    private static void test(int[] nums, int expect) {
        Solution solution = new Solution();
        int output = solution.maximumPrimeDifference(nums);
        String desc = String.format("maximum prime difference nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
