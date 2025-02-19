package org.leetcode.p2376;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/count-special-integers/
class Solution {
    public int countSpecialNumbers(int n) {
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        test(20, 19);
        test(5, 5);
        test(135, 110);
    }

    private static void test(int n, int expect) {
        Solution solution = new Solution();
        int output = solution.countSpecialNumbers(n);
        String desc = String.format("count special numbers n=%d", n);
        LeetCodeUtils.test(desc, output, expect);
    }
}
