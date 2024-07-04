package org.leetcode.p3099;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/harshad-number
class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0;
        int temp = x;
        while (temp > 0) {
            sum += temp % 10;
            temp /= 10;
        }
        if (x % sum == 0) {
            return sum;
        } else {
            return -1;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        test(18, 9);
        test(23, -1);
    }

    private static void test(int x, int expect) {
        Solution solution = new Solution();
        int output = solution.sumOfTheDigitsOfHarshadNumber(x);
        String desc = String.format("sum of digits x=%d", x);
        LeetCodeUtils.test(desc, output, expect);
    }
}
