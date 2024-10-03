package org.leetcode.p0983;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// I think we should use dynamic programming to solve this problem.
// To start with, we can create a function, which takes the index of days, and returns the min cost we can get.
// For the 1 day ticket, f(i) should directly equal to f(i-1) + costs[0], very straight forward.
// For the 7 days ticket, f(i) should equal to f(prev7) + costs[1], the previous index here means the previous day that is 7 days before.
// For the 30 days ticket, things should be quite similar, f(prev30) + costs[2]
// f(i) = min(f....)
// And next we need to find out how to calculate prev7 & prev30

// I think the main idea should be correct. Let's start coding.
//


// https://leetcode.cn/problems/minimum-cost-for-tickets/?envType=daily-question&envId=2024-10-01
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        final int N = days.length;
        int[] dp = new int[N + 1];
        for (int i = 0; i < N; i++) {
            dp[i + 1] = dp[i] + costs[0];
            int prev7 = -1;
            int prev30 = -1;
            int j = i - 1;
            for (; j >= 0; j--) {
                if (days[i] - days[j] >= 7) {
                    prev7 = j;
                    break;
                }
            }
            dp[i + 1] = Math.min(dp[i + 1], dp[prev7 + 1] + costs[1]);
            // 30
            for (; j >= 0; j--) {
                if (days[i] - days[j] >= 30) {
                    prev30 = j;
                    break;
                }
            }
            dp[i + 1] = Math.min(dp[i + 1], dp[prev30 + 1] + costs[2]);
        }
        return dp[N];
    }
}

//class Solution {
//    int[] days;
//    int[] costs;
//    Map<Integer, Integer> map;
//
//    public int mincostTickets(int[] days, int[] costs) {
//        this.days = days;
//        this.costs = costs;
//        this.map = new HashMap<>();
//        int ans = dfs(days.length - 1);
//        return ans;
//    }
//
//    int dfs(int i) {
//        if (map.containsKey(i)) {
//            return map.get(i);
//        }
//        if (i < 0) {
//            map.put(i, 0);
//            return 0;
//        }
//        if (i == 0) {
//            int ans = Math.min(Math.min(costs[0], costs[1]), costs[2]);
//            map.put(0, ans);
//            return ans;
//        }
//        int currDay = days[i];
//        int ans = dfs(i - 1) + costs[0];
//        // 7
//        boolean findPrev = false;
//        int prev7 = -1;
//        int prev30 = -1;
//        int j = i - 1;
//        for (; j >= 0; j--) {
//            if (days[i] - days[j] >= 7) {
//                prev7 = j;
//                break;
//            }
//        }
//        ans = Math.min(ans, dfs(prev7) + costs[1]);
//        // 30
//        for (; j >= 0; j--) {
//            if (days[i] - days[j] >= 30) {
//                prev30 = j;
//                break;
//            }
//        }
//        ans = Math.min(ans, dfs(prev30) + costs[2]);
//        map.put(i, ans);
//        return ans;
//    }
//}

public class Main {
    public static void main(String[] args) {
//        test(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}, 11);
//        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}, 17);
        test(new int[]{1, 2, 3, 4, 6, 8, 9, 10, 13, 14, 16, 17, 19, 21, 24, 26, 27, 28, 29}, new int[]{3, 14, 50}, 50);
    }

    private static void test(int[] days, int[] costs, int expect) {
        try {
            int output = new Solution().mincostTickets(days, costs);
            String desc = String.format("min cost days=%s costs=%s", Arrays.toString(days), Arrays.toString(costs));
            LeetCodeUtils.test(desc, output, expect);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
