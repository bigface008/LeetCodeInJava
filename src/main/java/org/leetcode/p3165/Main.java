package org.leetcode.p3165;

// https://leetcode.cn/problems/maximum-sum-of-subsequence-with-non-adjacent-elements/?envType=daily-question&envId=2024-10-31
class Solution {
    static final long MOD = (long) Math.pow(10, 9) + 7;

    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        final int N = nums.length;
        System.out.println("Integer.bitCount(N)=" + Integer.bitCount(N));
        System.out.println("32 - Integer.numberOfLeadingZeros(N)=" + (32 - Integer.numberOfLeadingZeros(N)));
//        long[][] t = new long[2 << Integer.bitCount(N)][4];
        long[][] t = new long[2 << (32 - Integer.numberOfLeadingZeros(N))][4];
        build(t, nums, 1, 0, N - 1);
        long ans = 0;
        for (int[] q : queries) {
            update(t, 1, 0, N - 1, q[0], q[1]);
            ans = (ans + t[1][3]) % MOD;
        }
        return (int) ans;
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

    private void update(long[][] t, int o, int l, int r, int idx, int val) {
        if (l == r) {
            t[o][3] = Math.max(val, 0);
            return;
        }
        int m = (l + r) / 2;
        if (idx <= m) {
            update(t, o * 2, l, m, idx, val);
        } else {
            update(t, o * 2 + 1, m + 1, r, idx, val);
        }
        maintain(t, o);
    }

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
//    static final long MOD = (long) Math.pow(10, 9) + 7;
//
//    public int maximumSumSubsequence(int[] nums, int[][] queries) {
//        final int N = nums.length;
//        long[][] t = new long[2 << (32 - Integer.numberOfLeadingZeros(N))][4];
//        build(t, nums, 1, 0, N - 1);
//
//        long ans = 0;
//        for (int[] q : queries) {
//            update(t, 1, 0, N - 1, q[0], q[1]);
//            ans = (ans + t[1][3]) % MOD;
//        }
//        return (int) (ans % MOD);
//    }
//
//    private void build(long[][] t, int[] nums, int o, int l, int r) {
//        if (l == r) {
//            t[o][3] = Math.max(nums[l], 0);
//            return;
//        }
//        int m = (l + r) / 2;
//        build(t, nums, o * 2, l, m);
//        build(t, nums, o * 2 + 1, m + 1, r);
//        maintain(t, o);
//    }
//
//    private void update(long[][] t, int o, int l, int r, int i, int val) {
//        if (l == r) {
//            t[o][3] = Math.max(val, 0);
//            return;
//        }
//        int m = (l + r) / 2;
//        if (i <= m) {
//            update(t, o * 2, l, m, i, val);
//        } else {
//            update(t, o * 2 + 1, m + 1, r, i, val);
//        }
//        maintain(t, o);
//    }
//
//    private void maintain(long[][] t, int o) {
//        long[] a = t[o * 2];
//        long[] b = t[o * 2 + 1];
//        t[o][0] = Math.max(a[0] + b[2], a[1] + b[0]);
//        t[o][1] = Math.max(a[0] + b[3], a[1] + b[1]);
//        t[o][2] = Math.max(a[2] + b[2], a[3] + b[0]);
//        t[o][3] = Math.max(a[2] + b[3], a[3] + b[1]);
//    }
//}


//class Solution {
//    static final int MOD = (int) Math.pow(10, 9) + 7;
//
//    public int maximumSumSubsequence(int[] nums, int[][] queries) {
//        int ans = 0;
//        for (int[] q : queries) {
//            ans += sub(nums, q[0], q[1]);
//            ans %= MOD;
//        }
//        return ans;
//    }
//
//    int sub(int[] nums, int pos, int newValue) {
//        nums[pos] = newValue;
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
