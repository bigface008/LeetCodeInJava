package org.leetcode.p3098;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.com/problems/find-the-sum-of-subsequence-powers
class Solution {
    static final int MOD = 1000000007, INF = Integer.MAX_VALUE / 2;
    int K;
    int N;
    int[] nums;
    HashMap[][] dp;

    public int sumOfPowers(int[] nums, int k) {
        this.K = k;
        this.N = nums.length;
        this.nums = nums;
        Arrays.sort(nums);
        int ans = 0;
        this.dp = new HashMap[N][K+1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = new HashMap<Integer, Integer>();
            }
        }
        for (int i = 0; i < N; i++) {
            ans += dfs(i + 1, 1, INF);
        }
        return ans % MOD;
    }

    int dfs(int startIdx, int len, int energy) {
        if (startIdx >= N || len >= K) {
            return 0;
        }
        HashMap<Integer, Integer> mp = dp[startIdx][len];
        if (mp.containsKey(energy)) {
            return mp.get(energy);
        }
        if (len == K) {
            System.out.printf("startIdx=%d len=%d energy=%d res=%d\n", startIdx, len, energy, energy);
            mp.put(energy, energy);
            return energy;
        }
        int res = 0;
        for (int i = startIdx; i < N; i++) {
            if (len + N - i < K) {
                break;
            }
            res += dfs(i + 1, len + 1, Math.min(energy, nums[i] - nums[startIdx - 1]));
        }
        res = res % MOD;
        mp.put(energy, res);
        System.out.printf("startIdx=%d len=%d energy=%d calc res=%d\n", startIdx, len, energy, res);
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4}, 3, 4);
//        test(new int[]{2, 2}, 2, 0);
//        test(new int[]{4, 3, -1}, 2, 10);
//        test(new int[]{-24, -921, 119, -291, -65, 628, 372, 274, 962, -592, -10, 67, -977, 85, -294, 349, -119, -846, -959, -79, -877, 833, 857, 44, 826, -295, -855, 554, -999, 759, -653, -423, -599, -928}, 19, 0);
    }

    private static void test(int[] nums, int k, int expect) {
        Solution solution = new Solution();
        int output = solution.sumOfPowers(nums, k);
        String desc = String.format("sum of powers nums=%s, k=%d", Arrays.toString(nums), k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
