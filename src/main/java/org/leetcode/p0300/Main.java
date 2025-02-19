package org.leetcode.p0300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/longest-increasing-subsequence/
class Solution {
    public int lengthOfLIS(int[] nums) {
        final int N = nums.length;
        List<Integer> dp = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            int cur = nums[i];
            int idx = Collections.binarySearch(dp, cur);
            if (idx >= 0) {
                continue;
            }
            idx = -idx - 1;
            if (idx == dp.size()) {
                dp.add(cur);
            } else {
                dp.set(idx, cur);
            }
        }
        return dp.size();
    }
}

//class Solution {
//    public int lengthOfLIS(int[] nums) {
//        final int N = nums.length;
//        int[] dp = new int[N];
//        dp[0] = 1;
//        int ans = 1;
//        for (int i = 0; i < N; i++) {
//            int res = 1;
//            int cur = nums[i];
//            for (int j = i - 1; j >= 0; j--) {
//                if (nums[j] < cur) {
//                    res = Math.max(res, dp[j] + 1);
//                }
//            }
//            dp[i] = res;
//            ans = Math.max(ans, res);
//        }
//        return ans;
//    }
//}

//class Solution {
//    int[] nums;
//    int N;
//    int[] memo;
//
//    public int lengthOfLIS(int[] nums) {
//        this.nums = nums;
//        N = nums.length;
//        memo = new int[N];
//        Arrays.fill(memo, -1);
//        int ans = 0;
//        for (int i = 0; i < N; i++) {
//            ans = Math.max(ans, dfs(i));
//        }
//        return ans;
//    }
//
//    int dfs(int idx) {
//        if (memo[idx] != -1) {
//            return memo[idx];
//        }
//        if (idx == 0) {
//            memo[idx] = 1;
//            return 1;
//        }
//        int curr = nums[idx];
//        int ans = 1;
//        for (int i = idx - 1; i >= 0; i--) {
//            if (nums[i] < curr) {
//                ans = Math.max(ans, dfs(i) + 1);
//            }
//        }
//        memo[idx] = ans;
//        return ans;
//    }
//}

public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 9, 10);
        System.out.println(Collections.binarySearch(list, 7));
    }
}
