package org.leetcode.p0887;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/super-egg-drop/description/?envType=daily-question&envId=2024-10-14
class Solution {
    int[][] memo;

    public int superEggDrop(int k, int n) {
        memo = new int[k + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int ans = dfs(k, n);
//        System.out.println(Arrays.deepToString(memo));
        return ans;
    }

    int dfs(int eggsCnt, int floorCnt) {
        if (memo[eggsCnt][floorCnt] != -1) {
            return memo[eggsCnt][floorCnt];
        }
        if (eggsCnt == 1) {
            memo[eggsCnt][floorCnt] = floorCnt;
            return floorCnt;
        }
        if (floorCnt == 0) {
            memo[eggsCnt][floorCnt] = 0;
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int low = 1, high = floorCnt;
        while (low + 1 < high) {
            int f = (low + high) / 2;
            int r1 = dfs(eggsCnt - 1, f - 1);
            int r2 = dfs(eggsCnt, floorCnt - f);
            if (r1 == r2) {
                low = f;
                high = f;
            } else if (r1 < r2) {
                low = f;
            } else {
                high = f;
            }
        }
        for (int i = low; i <= high; i++) {
            int temp = Math.max(dfs(eggsCnt, floorCnt - i), dfs(eggsCnt - 1, i - 1));
            ans = Math.min(ans, temp + 1);
        }
        memo[eggsCnt][floorCnt] = ans;
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
//        test(1, 2, 2);
//        test(2, 2, 2);
        test(5, 10000, 0);
    }

    private static void test(int k, int n, int expect) {
        int output = new Solution().superEggDrop(k, n);
        String desc = String.format("egg k=%d n=%d", k, n);
        LeetCodeUtils.test(desc, output, expect);
    }
}
