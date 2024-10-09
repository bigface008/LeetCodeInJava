package org.leetcode.p0673;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

// https://leetcode.com/problems/number-of-longest-increasing-subsequence/
class Solution {
    public int findNumberOfLIS(int[] nums) {
        final int N = nums.length;
        int[][] dp = new int[2][N]; // 0->len 1->cnt
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        dp[0][0] = 1;
        dp[1][0] = 1;

        int maxLisLen = 0;
        int maxLisCnt = 0;
        for (int idx = 0; idx < N; idx++) {
            int x = nums[idx];
            int lisLen = 0;
            int lisCnt = 0;
            for (int i = 0; i < idx; i++) {
                if (x > nums[i]) {
                    int currLen = dp[0][i] + 1;
                    if (currLen > lisLen) {
                        lisLen = currLen;
                        lisCnt = dp[1][i];
                    } else if (currLen == lisLen) {
                        lisCnt += dp[1][i];
                    }
                }
            }
            dp[0][idx] = lisLen;
            dp[1][idx] = lisCnt;
            if (lisLen > maxLisLen) {
                maxLisLen = lisLen;
                maxLisCnt = lisCnt;
            } else if (lisLen == maxLisLen) {
                maxLisCnt += lisCnt;
            }
        }
        return maxLisCnt;
    }
}


class Solution2 {
    int[] nums;
    int[] memo1;
    int[] memo2;
//    int maxLen = 0;

    public int findNumberOfLIS(int[] nums) {
        final int N = nums.length;
        this.nums = nums;
        this.memo1 = new int[N]; // len
        this.memo2 = new int[N]; // cnt
        Arrays.fill(memo1, -1);
        Arrays.fill(memo2, -1);
        for (int i = 0; i < N; i++) {
            dfs2(i);
        }
        int maxLen = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (memo1[i] > maxLen) {
                maxLen = memo1[i];
                cnt = memo2[i];
            } else if (memo1[i] == maxLen) {
                cnt += memo2[i];
            }
        }
        return cnt;
    }

    int dfs2(int idx) {
        if (idx == 0) {
            memo1[0] = 1;
            memo2[0] = 1;
            return 1;
        }
        if (memo2[idx] != -1) {
            return memo2[idx];
        }
        int x = nums[idx];
        int lisLen = 1;
        int lisCnt = 1;
        for (int i = 0; i < idx; i++) {
            if (x > nums[i]) {
                int currLen = dfs1(i) + 1;
                if (currLen > lisLen) {
                    lisLen = currLen;
                    lisCnt = dfs2(i);
                } else if (currLen == lisLen) {
                    lisCnt += dfs2(i);
                }
            }
        }
        memo1[idx] = lisLen;
        memo2[idx] = lisCnt;
        return lisCnt;
    }

    // len
    int dfs1(int idx) {
        if (idx == 0) {
            return 1;
        }
        if (memo1[idx] != -1) {
            return memo1[idx];
        }
        int x = nums[idx];
        int ans = 1;
        for (int i = 0; i < idx; i++) {
            if (x > nums[i]) {
                ans = Math.max(ans, dfs1(i) + 1);
            }
        }
        memo1[idx] = ans;
        return ans;
    }
}

//class Solution {
//    public int findNumberOfLIS(int[] nums) {
//        Stack<Integer> stk = new Stack<>();
//        final int N = nums.length;
//        int lisLen = 0;
//        int lisCnt = 0;
//        for (int i = 0; i < N; i++) {
//            int x = nums[i];
//            while (!stk.isEmpty() && stk.peek() >= x) {
//                stk.pop();
//            }
//            stk.add(x);
//            if (lisLen == stk.size()) {
//                lisCnt++;
//            } else if (lisLen < stk.size()) {
//                lisLen = stk.size();
//                lisCnt = 1;
//            }
//        }
//        return lisCnt;
//    }
//}

public class Main {
    public static void main(String[] args) {
        test(new int[]{2, 2, 2, 2, 2}, 5);
    }

    static void test(int[] nums, int expect) {
        int output = new Solution().findNumberOfLIS(nums);
        String desc = String.format("find nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
