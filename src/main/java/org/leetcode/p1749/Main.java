package org.leetcode.p1749;


// dp[i]
//   if nums[i] >= 0:
//      = dp[i - 1] + nums[i]
//   else:
//      = nums[i]

// https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/?envType=daily-question&envId=2025-02-26
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        final int N = nums.length;
        int[] dp1 = new int[N];
        int[] dp2 = new int[N];
        int ans = Math.abs(nums[0]);
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        for (int i = 1; i < N; i++) {
            int x = nums[i];
            int prev1 = dp1[i - 1];
            if (prev1 >= 0) {
                dp1[i] = prev1 + x;
            } else {
                dp1[i] = x;
            }
            int prev2 = dp2[i - 1];
            if (prev2 <= 0) {
                dp2[i] = prev2 + x;
            } else {
                dp2[i] = x;
            }
            ans = Math.max(Math.abs(dp2[i]), Math.max(ans, dp1[i]));
        }
        return ans;
    }
}

public class Main {
}
