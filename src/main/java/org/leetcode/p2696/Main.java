package org.leetcode.p2696;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/minimum-string-length-after-removing-substrings/?envType=daily-question&envId=2024-10-07
class Solution {
    public int minLength(String s) {
        int i = 0;
        while (i + 1 < s.length()) {
            String subStr = s.substring(i, i + 2);
            if (subStr.equals("AB") || subStr.equals("CD")) {
                if (i + 1 == s.length() - 1) {
                    s = s.substring(0, i);
                } else {
                    s = s.substring(0, i) + s.substring(i + 2);
                }
                if (i > 0) {
                    i--;
                }
            } else {
                i++;
            }
//            char ch1 = s.charAt(i), ch2 = s.charAt(i + 1);
//            if ((ch1 == 'A' && ch2 == 'B') || (ch1 == 'C' && ch2 == 'D')) {
//                if (i + 1 == s.length() - 1) {
//                    s = s.substring(0, i);
//                } else {
//                    s = s.substring(0, i) + s.substring(i + 2);
//                }
//            }
        }
        return s.length();
    }
}

public class Main {
    public static void main(String[] args) {
        test("ABFCACDB", 2);
    }

    static void test(String s, int expect) {
        int output = new Solution().minLength(s);
        String desc = String.format("min length s=%s", s);
        LeetCodeUtils.test(desc, output, expect);
    }
}
