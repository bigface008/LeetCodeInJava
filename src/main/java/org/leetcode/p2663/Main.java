package org.leetcode.p2663;

import org.leetcode.utils.LeetCodeUtils;

class Solution {
    public String smallestBeautifulString(String s, int k) {
        if (s.isEmpty()) return s;
        int i = s.length() - 1;
        StringBuilder ans = new StringBuilder(s);
        while (i >= 0) {
            char c = s.charAt(i);
            int start = c - 'a';
//            boolean canIncrease = false;
            for (int j = start + 1; j < k; j++) {
//                canIncrease = true;
                ans.setCharAt(i, (char)('a' + j));
                String temp = ans.toString();
                if (isBeautiful(temp)) {
                    return temp;
                }
            }
            ans.setCharAt(i, 'a');
            i--;
        }
        return "";
    }

    private boolean isBeautiful(String s) {
        if (s.length() <= 1) return true;
        int len = s.length();
        int half = len / 2;
        for (int i = 0; i < half; i++) {
            char curr = s.charAt(i);
            char another = s.charAt(len - i - 1);
            if (curr != another) return true;
        }
        return false;
    }
}


class SolutionV2 {
    public String smallestBeautifulString(String s, int k) {
        if (s.isEmpty()) return s;
        int i = s.length() - 1;
        StringBuilder ans = new StringBuilder(s);
        while (i >= 0) {
            char c = s.charAt(i);
            int start = c - 'a';
//            boolean canIncrease = false;
            for (int j = start + 1; j < k; j++) {
//                canIncrease = true;
                ans.setCharAt(i, (char)('a' + j));
                String temp = ans.toString();
                if (isBeautiful(temp)) {
                    return temp;
                }
            }
            ans.setCharAt(i, 'a');
            i--;
        }
        return "";
    }

    private boolean isBeautiful(String s) {
        if (s.length() <= 1) return true;
        int len = s.length();
        int half = len / 2;
        for (int i = 0; i < half; i++) {
            char curr = s.charAt(i);
            char another = s.charAt(len - i - 1);
            if (curr != another) return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
//        test("abcz", 26, "abda");
//        test("dc", 4, "");
        test("ced", 6, "cef");
    }

    private static void test(String s, int k, String expect) {
        String desc = String.format("s=%s, k=%d", s, k);
        var solution = new SolutionV2();
        String output = solution.smallestBeautifulString(s, k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
