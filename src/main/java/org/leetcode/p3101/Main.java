package org.leetcode.p3101;

// https://leetcode.com/problems/count-alternating-subarrays/
class Solution {
    public long countAlternatingSubarrays(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        long ans = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                dp[i] = 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
            ans += dp[i];
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }

    private static void test() {}
}
