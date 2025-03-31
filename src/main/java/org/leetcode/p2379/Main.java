package org.leetcode.p2379;

// https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/?envType=daily-question&envId=2025-03-08
class Solution {
    public int minimumRecolors(String blocks, int k) {
        final int N = blocks.length();
        if (N < k) {
            return 0;
        }
        int windowWhiteCnt = 0;
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                windowWhiteCnt++;
            }
        }
        int ans = windowWhiteCnt;
        for (int i = k; i < N; i++) {
            if (blocks.charAt(i) == 'W') {
                windowWhiteCnt++;
            }
            if (blocks.charAt(i - k) == 'W') {
                windowWhiteCnt--;
            }
            ans = Math.min(ans, windowWhiteCnt);
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        int a = 12;
        System.out.println(Integer.bitCount(a));
        System.out.println(Integer.numberOfLeadingZeros(a));
        System.out.println(Integer.numberOfTrailingZeros(a));
    }
}
