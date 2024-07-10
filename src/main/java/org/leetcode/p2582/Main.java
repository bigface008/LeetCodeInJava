package org.leetcode.p2582;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/pass-the-pillow
class Solution {
    public int passThePillow(int n, int time) {
        int times = time / (n - 1);
        int remain = time % (n - 1);
        if (times % 2 == 0) {
            return remain + 1;
        } else {
            return n - remain;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        test(4, 5, 2);
        test(3, 2, 3);
    }

    private static void test(int n, int time, int expect) {
        Solution solution = new Solution();
        int output = solution.passThePillow(n, time);
        String desc = String.format("passThePillow(%s, %s) = %s, expect %s", n, time, output, expect);
        LeetCodeUtils.test(desc, output, expect);
    }
}
