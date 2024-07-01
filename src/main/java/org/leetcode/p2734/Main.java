package org.leetcode.p2734;

import org.leetcode.utils.LeetCodeUtils;

class Solution {
    public String smallestString(String s) {
        if (s.isEmpty()) {
            return s;
        }
        StringBuilder ans = new StringBuilder(s);
        boolean inLeadingA = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                if (inLeadingA) {
                    if (i == s.length() - 1) {
                        ans.setCharAt(i, 'z');
                    } else {
                        continue;
                    }
                } else {
                    break;
                }
            } else {
                if (inLeadingA) {
                    inLeadingA = false;
                }
                ans.setCharAt(i, (char)(s.charAt(i) - 1));
            }
        }
        return ans.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        test("cbabc", "baabc");
        test("acbbc", "abaab");
        test("leetcode", "kddsbncd");
    }

    private static void test(String s, String expect) {
        Solution solution = new Solution();
        String output = solution.smallestString(s);
        String desc = String.format("smallest string s=%s", s);
        LeetCodeUtils.test(desc, output, expect);
    }
}
