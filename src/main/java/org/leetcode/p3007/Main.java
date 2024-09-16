package org.leetcode.p3007;

// https://leetcode.cn/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k/description/
class Solution {
    int x;
    public long findMaximumNumber(long k, int x) {
        this.x = x;
        return 0;
    }

    int calc(int n) {
        int i = x - 1;
        int len = Integer.bitCount(n);
        int res = 0;
        while (i < len) {
            res += ((1 << i) & n ) == 0 ? 0 : 1;
            i += x;
        }
        return res;
    }
}

public class Main {
}
