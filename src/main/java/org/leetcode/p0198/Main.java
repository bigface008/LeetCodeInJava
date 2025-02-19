package org.leetcode.p0198;

// https://leetcode.cn/problems/house-robber/
class Solution {
    public int rob(int[] nums) {
        final int N = nums.length;
        long[][] t = new long[2 << (32 - Integer.numberOfLeadingZeros(N))][4];
        build(t, nums, 1, 0, N - 1);
        return (int) t[1][3];
    }

    private void build(long[][] t, int[] nums, int o, int l, int r) {
        if (l == r) {
            t[o][3] = Math.max(nums[l], 0);
            return;
        }
        int m = (l + r) / 2;
        build(t, nums, o * 2, l, m);
        build(t, nums, o * 2 + 1, m + 1, r);
        maintain(t, o);
    }

    // f00(A) = max(f01(p) + f00(q), f00(p) + f10(q))
    // f01(A) = max(f01(p) + f01(q), f00(p) + f11(q))
    // f10(A) = max(f11(p) + f00(q), f10(p) + f10(q))
    // f11(A) = max(f10(p) + f11(q), f11(p) + f01(q))

    private void maintain(long[][] t, int o) {
        long[] a = t[o * 2];
        long[] b = t[o * 2 + 1];
        t[o][0] = Math.max(a[1] + b[0], a[0] + b[2]);
        t[o][1] = Math.max(a[1] + b[1], a[0] + b[3]);
        t[o][2] = Math.max(a[3] + b[0], a[2] + b[2]);
        t[o][3] = Math.max(a[2] + b[3], a[3] + b[1]);
    }
}

//class Solution {
//    public int rob(int[] nums) {
//        final int N = nums.length;
//        if (N == 1) {
//            return Math.max(nums[0], 0);
//        }
//        int[][] dp = new int[2][N];
//        dp[1][0] = nums[0];
//        dp[1][1] = nums[1];
//        dp[0][1] = Math.max(nums[0], 0);
//        for (int i = 2; i < N; i++) {
//            int cur = nums[i];
//            dp[1][i] = dp[0][i - 1] + cur;
//            dp[0][i] = Math.max(dp[1][i - 1], dp[0][i - 1]);
//        }
//        return Math.max(dp[0][N - 1], dp[1][N - 1]);
//    }
//}

public class Main {
}
