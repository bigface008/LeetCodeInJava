package org.leetcode.p0008;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/string-to-integer-atoi/description/
class Solution {
    public int myAtoi(String s) {
        final int LETTER_NUM = 10;
        boolean inNum = false;
        int numStartIdx = 0;
        boolean negative = false;
        long num = 0;
        final long limit = 1L << 31;
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int diff = ch - '0';
            boolean isNum = checkNum(ch);
            if (isNum || ch == '+' || ch == '-') {
                if (!inNum) {
                    inNum = true;
                    numStartIdx = i;
                }
                if (inNum) {
                    if (ch == '-') {
                        if (i != numStartIdx) {
                            break;
                        }
                        if (i != 0 && checkNum(s.charAt(i - 1))) {
                            break;
                        }
                        if (i != s.length() - 1 && !checkNum(s.charAt(i + 1))) {
                            break;
                        }
                        negative = true;
                    } else if (ch == '+') {
                        if (i != numStartIdx) {
                            break;
                        }
                        if (i != s.length() - 1 && !checkNum(s.charAt(i + 1))) {
                            break;
                        }
                    } else if (isNum) {
                        num = num * 10 + diff;
                        if (!negative) {
                            if (num > limit - 1) {
                                num = limit - 1;
                                break;
                            }
                        } else {
                            if (num > limit) {
                                num = limit;
                                break;
                            }
                        }
                    }
                }
            } else {
                break;
            }
        }
        if (negative) {
            num = -num;
        }
//        if (num > limit - 1) {
//            num = limit - 1;
//        }
//        if (num < -limit) {
//            num = -limit;
//        }
        return (int)num;
    }

    private boolean checkNum(char ch) {
        int diff = ch - '0';
        return diff >= 0 && diff <= 9;
    }
}

public class Main {
    public static void main(String[] args) {
        test("42", 42);
        test(" -042", -42);
        test("1337c0d3", 1337);
        test("0-1", 0);
        test("words and 987", 0);
        test("21474836460", 2147483647);
        test("   +0 123", 0);
        test("9223372036854775808", 2147483647);
        test("-13+8", -13);
    }

    private static void test(String s, int expect) {
        Solution solution = new Solution();
        int output = solution.myAtoi(s);
        String desc = String.format("atoi s=%s", s);
        LeetCodeUtils.test(desc, output, expect);
    }
}
