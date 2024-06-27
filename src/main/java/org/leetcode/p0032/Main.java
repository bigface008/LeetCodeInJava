package org.leetcode.p0032;

import org.leetcode.utils.LeetCodeUtils;

class Solution {
    public int longestValidParentheses(String s) {

    }
}

public class Main {
    public static void main(String[] args) {
        test("(()", 2);
        test(")()())", 4);
    }

    private static void test(String s, int expect) {
        Solution solution = new Solution();
        int output = solution.longestValidParentheses(s);
        String desc = String.format("longest valid parentheses s=%s", s);
        LeetCodeUtils.test(desc, output, expect);
    }
}
