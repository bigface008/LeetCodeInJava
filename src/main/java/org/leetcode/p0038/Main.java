package org.leetcode.p0038;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/count-and-say/
class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String sub = countAndSay(n - 1);
        if (sub.isEmpty()) {
            return ""; // ?
        }
        List<List<Integer>> list = new ArrayList<>();
        char prevCh = sub.charAt(0);
        int chCnt = 1;
        for (int i = 1; i < sub.length(); i++) {
            char ch = sub.charAt(i);
            if (prevCh == ch) {
                chCnt++;
            } else {
                list.add(List.of(prevCh - '0', chCnt));
                chCnt = 1;
                prevCh = ch;
            }
        }
        list.add(List.of(prevCh - '0', chCnt));

        StringBuilder builder = new StringBuilder();
        for (List<Integer> p : list) {
            builder.append((char)(p.get(1) + '0'));
            builder.append((char)(p.get(0) + '0'));
        }
        return builder.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        test(4, "1211");
        test(1, "1");
    }

    private static void test(int n, String expect) {
        Solution solution = new Solution();
        String output = solution.countAndSay(n);
        String desc = String.format("count and say n=%d", n);
        LeetCodeUtils.test(desc, output, expect);
    }
}
