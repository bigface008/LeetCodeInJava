package org.leetcode.p1328;

// https://leetcode.cn/problems/break-a-palindrome/?envType=daily-question&envId=2025-03-05
class Solution {
    public String breakPalindrome(String palindrome) {
        final int N = palindrome.length();
        if (N <= 1) {
            return "";
        }
        char[] chs = palindrome.toCharArray();
        for (int i = 0; i < N / 2; i++) {
            if (chs[i] != 'a') {
                chs[i] = 'a';
                return new String(chs);
            }
        }
        chs[N - 1] = 'b';
        return new String(chs);
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
