package org.leetcode.p1598;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/problems/crawler-log-folder
class Solution {
    public int minOperations(String[] logs) {
        int depth = 0;
        Stack<String> stk = new Stack<>();
        for (String log : logs) {
            if (log.equals("../")) {
                if (!stk.isEmpty()) {
                    stk.pop();
                }
            } else if (log.equals("./")) {
                continue;
            } else {
                stk.add(log);
            }
        }
        return stk.size();
    }
}

public class Main {
    public static void main(String[] args) {
        test(new String[]{"d1/", "d2/", "../", "d21/", "./"}, 2);
        test(new String[]{"d1/", "d2/", "./", "d3/", "../", "d31/"}, 3);
        test(new String[]{"d1/", "../", "../", "../"}, 0);
    }

    private static void test(String[] logs, int expect) {
        Solution solution = new Solution();
        int output = solution.minOperations(logs);
        String desc = String.format("min operations logs=%s", Arrays.toString(logs));
        LeetCodeUtils.test(desc, output, expect);
    }
}
