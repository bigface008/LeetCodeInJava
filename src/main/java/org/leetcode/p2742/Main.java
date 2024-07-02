package org.leetcode.p2742;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

// https://leetcode.com/problems/painting-the-walls/description/
class Solution {
    public int paintWalls(int[] cost, int[] time) {
        PriorityQueue<Info> minHeap = new PriorityQueue<>(cost.length, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.cost - o2.cost;
            }
        });
        for (int i = 0; i < cost.length; i++) {
            minHeap.add(new Info(time[i], cost[i]));
        }
        int paidWallCnt = 0;
        int paidWallTotalCost = 0;
        int paidWallTotalTime = 0;
        while (!minHeap.isEmpty()) {
            if (paidWallTotalTime >= time.length - paidWallCnt) {
                return paidWallTotalCost;
            }
            Info head = minHeap.poll();
            paidWallCnt++;
            paidWallTotalCost += head.cost;
            paidWallTotalTime += head.time;
        }
        return paidWallTotalCost;
    }

    class Info {
        int time;
        int cost;

        Info(int time, int cost) {
            this.time = time;
            this.cost = cost;
        }
    }
}

// 免费刷墙数目 + 付费刷墙数目 = n 付费刷墙时间 >= 免费刷墙数目 刷墙的合法方案满足：付费刷墙时间 >= n - 付费刷墙数目 即：付费刷墙时间 + 付费刷墙数目 >= n
// 因此，问题转化为 从n个墙里面选择要付费刷的墙，满足上面的等式的最小cost
// f[i][j] 代表从 0 - i 的墙中选，/sigma >= j的最小cost，那么最终的答案就是f[n-1][n]
// 状态转移方程： 选了第i个： f[i][j] =f[i-1][j - (time[i] + 1)] + cost[i] 没有选第i个 f[i][j] = f[i-1][j]
// 状态初始化： f[0][j] = { cost[0] if j <= time[0]+1 0 if j = 0 INF else }
// 需要注意的点：
// 可能会觉得只有 j >= time[i] + 1的时候 选第i个墙的状态转移才会出现 但这是错的 当j < time[i] + 1的时候 选择第i个墙 是必定可以满足不等式的
// 此时f[i][j] = f[i][0] + cost[i]
class SolutionV2 {
    public int paintWalls(int[] cost, int[] time) {
        final int N = cost.length;
        int[][] dp = new int[N][N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        for (int i = 0; i <= N; i++) {
            if (i <= time[0] + 1) {
                dp[0][i] = cost[0];
            }
        }
        dp[0][0] = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][Math.max(0, j - time[i] - 1)] + cost[i]);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
            }
        }
        return dp[N - 1][N];
    }
};

public class Main {
    public static void main(String[] args) {
//        test(new int[]{1, 2, 3, 2}, new int[]{1, 2, 3, 2}, 3);
//        test(new int[]{2, 3, 4, 2}, new int[]{1, 1, 1, 1}, 4);
        test(new int[]{26, 53, 10, 24, 25, 20, 63, 51}, new int[]{1, 1, 1, 1, 2, 2, 2, 1}, 55);
    }

    private static void test(int[] cost, int[] time, int expect) {
        Solution solution = new Solution();
        int output = solution.paintWalls(cost, time);
        String desc = String.format("paint walls cost=%s time=%s", Arrays.toString(cost), Arrays.toString(time));
        LeetCodeUtils.test(desc, output, expect);
    }
}
