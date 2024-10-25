package org.leetcode.p1535;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

class Solution {
    public int getWinner(int[] arr, int k) {
        final int N = arr.length;
        if (k > N) {
            k = N;
        }
        int mx_i = 0;
        int mx_cnt = 0;
        for (int i = 1; i < N; i++) {
            if (arr[i] < arr[mx_i]) {
                mx_cnt++;
            } else {
                mx_i = i;
                mx_cnt = 1;
            }
            if (mx_cnt >= k) {
                break;
            }
        }
        return arr[mx_i];
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{2, 1, 3, 5, 4, 6, 7}, 2, 5);
    }

    private static void test(int[] arr, int k, int expect) {
        int output = new Solution().getWinner(arr, k);
        String desc = String.format("winner arr=%s k=%d", Arrays.toString(arr), k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
