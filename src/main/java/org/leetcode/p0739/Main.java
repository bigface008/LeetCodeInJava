package org.leetcode.p0739;

import java.util.Stack;

// https://leetcode.com/problems/daily-temperatures/
class Solution {
    // ->
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> monoStk = new Stack<>();
        int N = temperatures.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            int temp = temperatures[i];
            while (!monoStk.isEmpty() && temperatures[monoStk.peek()] < temp) {
                int idx = monoStk.pop();
                ans[idx] = i - idx;
            }
            monoStk.add(i);
        }
        return ans;
    }

    // <-
//    public int[] dailyTemperatures(int[] temperatures) {
//        Stack<Integer> monoStk = new Stack<>();
//        int N = temperatures.length;
//        int[] ans = new int[N];
//        for (int i = N - 1; i >= 0; i--) {
//            int temp = temperatures[i];
//            while (!monoStk.isEmpty() && temperatures[monoStk.peek()] <= temp) {
//                monoStk.pop();
//            }
//            if (monoStk.isEmpty()) {
//                ans[i] = 0;
//            } else {
//                ans[i] = monoStk.peek() - i;
//            }
//            monoStk.add(i);
//        }
//        return ans;
//    }
}

public class Main {
}
