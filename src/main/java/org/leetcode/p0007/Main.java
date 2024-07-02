package org.leetcode.p0007;

import org.leetcode.utils.LeetCodeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
class Solution {
    public int reverse(int x) {
        boolean negative = x < 0;
        x = Math.abs(x);
        int ans = 0;
        while (x > 0) {
            int temp = x % 10;
            if (negative && ans > (Integer.MAX_VALUE - temp) / 10) {
                ans = 0;
                break;
            } else if (!negative && ans > (Integer.MAX_VALUE - 1 - temp) / 10) {
                ans = 0;
                break;
            }
            ans = ans * 10 + temp;
            x = x / 10;
        }
        if (negative) {
            ans = -ans;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
//        test(123, 321);
//        test(-123, -321);
//        test(120, 21);
        test(-10, -1);
    }

    private static void test(int x, int expect) {
        Solution solution = new Solution();
        int output = solution.reverse(x);
        String desc = String.format("length of longest substring x=%d", x);
        LeetCodeUtils.test(desc, output, expect);
    }
}
