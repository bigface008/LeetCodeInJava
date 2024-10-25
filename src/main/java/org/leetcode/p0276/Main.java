package org.leetcode.p0276;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/paint-fence/?envType=weekly-question&envId=2024-10-01
class Solution {
//    int[] fenceColors;
//    int ans;
//    int colorCnt;
//    int[][] memo;

    public int numWays(int n, int k) {
//        memo = new int[2][n];
//        Arrays.fill(memo[0], -1);
//        Arrays.fill(memo[1], -1);
//        colorCnt = k;
//        int ans = dfs(n - 1, 0) + dfs(n - 1, 1);
//        System.out.println(Arrays.deepToString(memo));
//        return ans;
        int[][] dp = new int[2][n];
        dp[0][0] = k;
        for (int i = 1; i < n; i++) {
            dp[1][i] = dp[0][i - 1];
            dp[0][i] = (dp[0][i - 1] + dp[1][i - 1]) * (k - 1);
        }
        return dp[0][n - 1] + dp[1][n - 1];
    }

//    private int dfs(int fenceIdx, int twoSameColor) {
//        if (memo[twoSameColor][fenceIdx] != -1) {
//            return memo[twoSameColor][fenceIdx];
//        }
//        if (fenceIdx == 0) { // no prev
//            if (twoSameColor == 1) {
//                memo[twoSameColor][fenceIdx] = 0;
//                return 0;
//            } else {
//                memo[twoSameColor][fenceIdx] = colorCnt;
//                return colorCnt;
//            }
//        }
//        int ans = 0;
//        if (twoSameColor == 1) {
//            ans = dfs(fenceIdx - 1, 0);
//        } else {
//            ans = (dfs(fenceIdx - 1, 0) + dfs(fenceIdx - 1, 1)) * (colorCnt - 1);
//        }
//        memo[twoSameColor][fenceIdx] = ans;
//        return ans;
//    }
}

//class Solution {
//    int[] fenceColors;
//    int ans;
//    int k;
//
//    public int numWays(int n, int k) {
//        fenceColors = new int[n];
//        ans = 0;
//        this.k = k;
//        dfs(0);
//        return ans;
//    }
//
//    void dfs(int fenceIdx) {
//        if (fenceIdx == fenceColors.length) {
//            ans++;
//            return;
//        }
//        for (int i = 1; i <= k; i++) {
//            int sameColorCnt = 0;
//            if (fenceIdx > 0 && fenceColors[fenceIdx - 1] == i) {
//                sameColorCnt++;
//            }
//            if (fenceIdx > 1 && fenceColors[fenceIdx - 2] == i) {
//                sameColorCnt++;
//            }
//            if (sameColorCnt == 2) {
//                continue;
//            }
//            fenceColors[fenceIdx] = i;
//            dfs(fenceIdx + 1);
//            fenceColors[fenceIdx] = 0;
//        }
//    }
//}

public class Main {
    public static void main(String[] args) {
        test(3, 2, 6);
    }

    private static void test(int n, int k, int expect) {
        int output = new Solution().numWays(n, k);
        String desc = String.format("n=%d k=%d", n, k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
