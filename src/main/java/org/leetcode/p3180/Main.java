package org.leetcode.p3180;

import org.leetcode.utils.LeetCodeUtils;

import java.math.BigInteger;
import java.util.Arrays;

class Solution {
    public int maxTotalReward(int[] rewardValues) {
        int m = 0;
        for (int v : rewardValues) {
            m = Math.max(m, v);
        }
        for (int v : rewardValues) {
            if (v == m - 1) {
                return m * 2 - 1;
            }
        }
        BigInteger f = BigInteger.ONE;
        for (int x : Arrays.stream(rewardValues).distinct().sorted().toArray()) {
            BigInteger mask = BigInteger.ONE.shiftLeft(x).subtract(BigInteger.ONE);
            f = f.or(f.and(mask).shiftLeft(x));
        }
        return f.bitLength() - 1;
    }
}


//class Solution {
//    public int maxTotalReward(int[] rewardValues) {
//        final int N = rewardValues.length;
//        Arrays.sort(rewardValues);
//        boolean[][] dp = new boolean[N + 1][4000];
//        dp[0][0] = true;
//        int ans = 0;
//        for (int i = 0; i < N; i++) {
//            int x = rewardValues[i];
//            for (int j = 0; j <= 3999; j++) {
//                dp[i + 1][j] = dp[i][j];
//                if (x > j - x && j - x >= 0) {
//                    dp[i + 1][j] |= dp[i][j - x];
//                }
//                if (i == N - 1 && dp[i + 1][j]) {
//                    ans = Math.max(ans, j);
//                }
//            }
//        }
//        return ans;
//    }
//}

//class Solution {
//    public int maxTotalReward(int[] rewardValues) {
//        final int N = rewardValues.length;
//        Arrays.sort(rewardValues);
//        int[][] dp = new int[N + 1][4000];
//        for (int i = N - 1; i >= 0; i--) {
//            int x = rewardValues[i];
//            for (int j = 3999; j >= 0; j--) {
//                dp[i][j] = dp[i + 1][j];
//                if (x > j) {
//                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j + x] + x);
//                }
//            }
//        }
//        return dp[0][0];
//    }
//}

public class Main {
    public static void main(String[] args) {
        test(new int[]{7}, 7);
    }

    private static void test(int[] rewardValues, int expect) {
        int output = new Solution().maxTotalReward(rewardValues);
        String desc = String.format("max total rewardValues=%s", Arrays.toString(rewardValues));
        LeetCodeUtils.test(desc, output, expect);
    }
}
