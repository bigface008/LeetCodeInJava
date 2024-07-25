package org.leetcode.p1186;

// dp
// dp[R] = max(arr[R], dp[R-1] + arr[R], dp[R-1])
// dp[R][0] j -> 0 -> not delete 1 -> delete

// dp[R][0] = max(arr[R], dp[R-1][0] + arr[R])
// dp[R][1] = max(dp[R-1][0], dp[R-1][1] + arr[R])

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;

// https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/description/?envType=daily-question&envId=2024-07-21
// 状态压缩
class Solution2 {
    public int maximumSum(int[] arr) {
        final int N = arr.length;
        final int INF = Integer.MAX_VALUE / 2;
        int dp0 = arr[0], dp1 = -INF;
        int ans = Math.max(dp0, dp1);
        for (int i = 1; i < N; i++) {
            dp1 = Math.max(dp0, dp1 + arr[i]);
            dp0 = Math.max(arr[i], dp0 + arr[i]);
            ans = Math.max(ans, Math.max(dp0, dp1));
        }
        return ans;
    }
}

class Solution {
    public int maximumSum(int[] arr) {
        final int N = arr.length;
        final int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[N][2];
        dp[0][0] = arr[0];
        dp[0][1] = -INF;
        int ans = Math.max(dp[0][0], dp[0][1]);
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(arr[i], dp[i - 1][0] + arr[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{2, 1, -2, -5, -2}, 3);
    }

    private static void test(int[] arr, int expect) {
        Solution solution = new Solution();
        int output = solution.maximumSum(arr);
        String desc = String.format("maximum sum %s", Arrays.toString(arr));
        LeetCodeUtils.test(desc, output, expect);
    }
}
