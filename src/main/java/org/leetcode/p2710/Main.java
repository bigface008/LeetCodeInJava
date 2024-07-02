package org.leetcode.p2710;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.cn/problems/remove-trailing-zeros-from-a-string/description
class Solution {
    public String removeTrailingZeros(String num) {
        int endIdx = num.length();
        for (int i = num.length() - 1; i >= 0; i--) {
            if (num.charAt(i) == '0') {
                endIdx--;
            } else {
                break;
            }
        }
        return num.substring(0, endIdx);
    }
}

public class Main {
    public static void main(String[] args) {
        test("51230100", "512301");
        test("123", "123");
    }

    private static void test(String num, String expect) {
        Solution solution = new Solution();
        String output = solution.removeTrailingZeros(num);
        String desc = String.format("remove trailing zeros num=%s", num);
        LeetCodeUtils.test(desc, output, expect);
    }
}
