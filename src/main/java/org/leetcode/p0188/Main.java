package org.leetcode.p0188;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
class Solution {
    public int maxProfit(int k, int[] prices) {
        final int N = prices.length;
        int[][] hold = new int[N][k + 1];
        int[][] notHold = new int[N][k + 1];
        Arrays.fill(hold[0], Integer.MIN_VALUE / 2);
        Arrays.fill(notHold[0], Integer.MIN_VALUE / 2);
        hold[0][0] = -prices[0];
        notHold[0][0] = 0;
        for (int i = 1; i < N; i++) {
            hold[i][0] = Math.max(hold[i - 1][0], notHold[i - 1][0] - prices[i]);
            for (int j = 1; j < k + 1; j++) {
                hold[i][j] = Math.max(hold[i - 1][j], notHold[i - 1][j] - prices[i]);
                notHold[i][j] = Math.max(notHold[i - 1][j], hold[i - 1][j - 1] + prices[i]);
            }
        }
        return Arrays.stream(notHold[N - 1]).max().getAsInt();
    }
}

public class Main {
    public static void main(String[] args) {
        test(2, new int[]{2, 4, 1}, 2);
        test(2, new int[]{3, 2, 6, 5, 0, 3}, 7);
    }

    private static void test(int k, int[] prices, int expect) {
        Solution solution = new Solution();
        int output = solution.maxProfit(k, prices);
        String desc = String.format("max profit k=%d prices=%s", k, Arrays.toString(prices), expect);
        LeetCodeUtils.test(desc, output, expect);
    }
}
