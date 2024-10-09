package org.leetcode.p0032;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        final int N = s.length();
        if (N == 0) {
            return 0;
        }
        int[] dp = new int[N];
        char[] chs = s.toCharArray();
        int res = 0;
        for (int i = 0; i < N; i++) {
            if (chs[i] == '(') {
                continue;
            }
            if (i == 1) {
                if (chs[0] == '(') {
                    dp[i] = 2;
                    res = Math.max(res, 2);
                }
                continue;
            }
            int stk = 1;
            int ans = 0;
            int j = i - 1;
            while (j >= 0) {
                if (chs[j] == ')') {
                    int len = dp[j];
                    if (len >= 2) {
                        if (j < len) {
                            if (stk == 0) {
                                ans = Math.max(ans, i - (j - len));
                            }
                            break;
                        }
                        if (stk == 0) {
                            ans = Math.max(ans, i - (j - len));
                        }
                        j -= len;
                    } else {
                        break;
                    }
                } else {
                    if (stk == 0) {
                        break;
                    } else {
                        stk--;
                        ans = Math.max(ans, i - j + 1);
                    }
                    j--;
                }
            }
            dp[i] = ans;
            res = Math.max(res, ans);
        }
        return res;
    }
}

//class Solution {
//    char[] chs;
//    int[] memo;
//    int res = 0;
//
//    public int longestValidParentheses(String s) {
//        final int N = s.length();
//        if (N == 0) {
//            return 0;
//        }
//        this.chs = s.toCharArray();
//        this.memo = new int[N];
//        Arrays.fill(memo, -1);
//        for (int i = 0; i < N; i++) {
//            dfs(i);
//        }
//        return res;
//    }
//
//    int dfs(int i) {
//        if (memo[i] != -1) {
//            return memo[i];
//        }
//        char curCh = chs[i];
//        if (curCh == '(') {
//            memo[i] = 0;
//            return 0;
//        }
//        if (i == 0) {
//            memo[i] = 0;
//            return 0;
//        }
//        if (i == 1) {
//            if (chs[0] == '(') {
//                memo[i] = 2;
//                res = Math.max(res, 2);
//                return 2;
//            } else {
//                memo[i] = 0;
//                return 0;
//            }
//        }
//        int stk = 1;
//        int ans = 0;
//        int j = i - 1;
//        while (j >= 0) {
//            if (chs[j] == ')') {
//                int len = dfs(j);
//                if (len >= 2) {
//                    if (j < len) {
//                        if (stk == 0) {
//                            ans = Math.max(ans, i - (j - len));
//                        }
//                        break;
//                    }
//                    if (stk == 0) {
//                        ans = Math.max(ans, i - (j - len));
//                    }
//                    j -= len;
//                } else {
//                    break;
//                }
//            } else {
//                if (stk == 0) {
//                    break;
//                } else {
//                    stk--;
//                    ans = Math.max(ans, i - j + 1);
//                }
//                j--;
//            }
//        }
//        memo[i] = ans;
//        res = Math.max(res, ans);
//        return ans;
//    }
//}

public class Main {
    public static void main(String[] args) {
        test("(()", 2);
        test(")()())", 4);
        test("()(()", 2);
        test("())", 2);
        test("()(())", 6);
        test("(()()", 4);
    }

    private static void test(String s, int expect) {
        Solution solution = new Solution();
        int output = solution.longestValidParentheses(s);
        String desc = String.format("longest valid parentheses s=%s", s);
        LeetCodeUtils.test(desc, output, expect);
    }
}
