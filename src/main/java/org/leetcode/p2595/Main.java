package org.leetcode.p2595;

class Solution {
    public int[] evenOddBit(int n) {
        int val = n;
        int even = 0, odd = 0;
        int i = 0;
        while (val > 0) {
            if ((val & 1) != 0) {
                if (i % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
            }
            val >>= 1;
            i++;
        }
        return new int[]{even, odd};
    }
}

public class Main {
}
