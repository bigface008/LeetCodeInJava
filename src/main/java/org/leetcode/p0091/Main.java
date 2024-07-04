package org.leetcode.p0091;

import org.leetcode.utils.LeetCodeUtils;

import java.util.HashMap;

// https://leetcode.com/problems/decode-ways/description/
class Solution {
    int waysCnt = 0;
    HashMap<Integer, Integer> map = new HashMap<>();

    public int numDecodings(String s) {
        backtrack(0, s);
        return waysCnt;
    }

    private void backtrack(int currIdx, String s) {
        if (currIdx == s.length()) {
            waysCnt++;
            return;
        }
        char ch = s.charAt(currIdx);
        if (ch == '1' || ch == '2') {
            if (currIdx != s.length() - 1) {
                char nextCh = s.charAt(currIdx + 1);
                if (ch == '1' || (ch == '2' && nextCh <= '6')) {
                    backtrack(currIdx + 2, s);
                }
                backtrack(currIdx + 1, s);
            } else {
                backtrack(currIdx + 1, s);
            }
        } else if (ch >= '3' && ch <= '9') {
            backtrack(currIdx + 1, s);
        }
    }
}


// https://leetcode.com/problems/decode-ways/description/
class SolutionV3 {
    public int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] dp = new int[s.length()];
        if (s.charAt(0) == '0') {
            dp[0] = 0;
        } else {
            dp[0] = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            char curr = s.charAt(i);
            char prev = s.charAt(i - 1);

        }
        return dp[s.length() - 1];
    }
}

//class SolutionV2 {
//    int waysCnt = 0;
//
//    public int numDecodings(String s) {
//        return 0;
//    }
//
//    private int rec(String s, HashMap<String, Integer> memo) {
//        if (s.isEmpty()) {
//            return 1;
//        }
//        if (memo.containsKey(s)) {
//            return memo.get(s);
//        }
//        char ch = s.charAt(0);
//        if (ch == '1' || ch == '2') {
//
//        } else if (ch >= '3' && ch <= '6') {
//            int temp = rec(s.substring(1), memo);
//            memo.put(s, temp);
//            return temp;
//        } else {
//            return 0;
//        }
//    }
//}

public class Main {
    public static void main(String[] args) {
        test("27", 1);
//        test("12", 2);
//        test("226", 3);
//        test("06", 0);
    }

    private static void test(String s, int expect) {
        Solution solution = new Solution();
        int output = solution.numDecodings(s);
        String desc = String.format("num decoding s=%s", s);
        LeetCodeUtils.test(desc, output, expect);
    }
}
