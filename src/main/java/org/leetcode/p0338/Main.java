package org.leetcode.p0338;

class Solution {
    public int[] countBits(int n) {
        if (n == 0) {
            return new int[1];
        }
        int[] ans = new int[n+1];
        ans[0] = 0;
        ans[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                ans[i] = ans[i / 2];
            } else {
                ans[i] = ans[(i - 1) / 2] + 1;
            }
        }
        return ans;
    }
}

class SolutionV2 {
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int j = i;
            while (j != 0) {
                result[i]++;
                j = j & (j - 1);
            }
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
