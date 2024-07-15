package org.leetcode.p0241;

import org.leetcode.utils.LeetCodeUtils;

import java.beans.IntrospectionException;
import java.util.*;

// https://leetcode.com/problems/different-ways-to-add-parentheses/description/
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '-' ||
                    input.charAt(i) == '*' ||
                    input.charAt(i) == '+' ) {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1);
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                for (Integer p1 :   part1Ret) {
                    for (Integer p2 :   part2Ret) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+': c = p1+p2;
                                break;
                            case '-': c = p1-p2;
                                break;
                            case '*': c = p1*p2;
                                break;
                        }
                        ret.add(c);
                    }
                }
            }
        }
        if (ret.size() == 0) {
            ret.add(Integer.valueOf(input));
        }
        Collections.sort(ret);
        return ret;
    }
}

public class Main {
    public static void main(String[] args) {
        test("2-1-1", "[0, 2]");
        test("2*3-4*5", "[-34, -14, -10, -10, 10]");
    }

    private static void test(String expression, String expectStr) {
        List<Integer> expect = LeetCodeUtils.makeIntList(expectStr);
        Solution solution = new Solution();
        List<Integer> output = solution.diffWaysToCompute(expression);
        String desc = String.format("diff ways to compute expression=%s", expression);
        LeetCodeUtils.test(desc, output, expect);
    }
}
