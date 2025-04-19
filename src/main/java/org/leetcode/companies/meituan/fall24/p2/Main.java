package org.leetcode.companies.meituan.fall24.p2;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// 2 1 5 3 4
// 5 4 3 1 2

// 2 1 4 5 3 4

// 给定一个整数数组，要求使用一个栈，只能按照数组从前往后的顺序入栈，出栈的顺序可以自己决定，要求出栈的元素可以组成最大的字典序

public class Main {
    public List<Integer> solve(int[] input) {
        final int N = input.length;
        Stack<Integer> stk = new Stack<>();
        int[] suffixMax = new int[N];
        suffixMax[N - 1] = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            if (input[suffixMax[i + 1]] <= input[i]) {
                suffixMax[i] = i;
            } else {
                suffixMax[i] = suffixMax[i + 1];
            }
        }

        int curMaxIdx = suffixMax[0];
        List<Integer> ans = new ArrayList<>(N);
        int i = 0;
        while (ans.size() < N) {
            while (i != curMaxIdx) {
                stk.add(input[i]);
                i++;
            }
            ans.add(input[curMaxIdx]);
            if (curMaxIdx + 1 >= N) {
                while (!stk.isEmpty()) {
                    ans.add(stk.pop());
                }
                break;
            }
            if (!stk.isEmpty() && stk.peek() >= input[suffixMax[curMaxIdx + 1]]) {
                ans.add(stk.pop());
            }
            curMaxIdx = suffixMax[curMaxIdx + 1];
            i++;
        }
        return ans;
    }

    private void check(int[] input, List<Integer> expect) {
        List<Integer> output = solve(input);
        String desc = String.format("input=%s", Arrays.toString(input));
        LeetCodeUtils.test(desc, output, expect);
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.check(new int[]{2, 1, 5, 3, 4}, List.of(5, 4, 3, 1, 2));
        m.check(new int[]{2, 1, 4, 5, 3, 4}, List.of(5, 4, 4, 3, 1, 2));
        m.check(new int[]{2, 1, 5, 6, 3, 4}, List.of(6, 5, 4, 3, 1, 2));
        m.check(new int[]{2, 1, 5, 5, 3, 4}, List.of(5, 5, 4, 3, 1, 2));
        m.check(new int[]{4, 1, 5, 3, 3, 5}, List.of(5, 5, 3, 3, 1, 4));
        m.check(new int[]{2}, List.of(2));
        m.check(new int[]{2, 1}, List.of(2, 1));
        m.check(new int[]{3, 2, 1}, List.of(3, 2, 1));
        m.check(new int[]{2, 3, 1}, List.of(3, 2, 1));
        m.check(new int[]{1, 3, 2}, List.of(3, 2, 1));
        m.check(new int[]{1, 2, 3}, List.of(3, 2, 1));
        m.check(new int[]{2, 1, 3}, List.of(3, 1, 2));
        m.check(new int[]{6, 1, 3, 3}, List.of(6, 3, 3, 1));
    }
}
