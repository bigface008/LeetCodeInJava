package org.leetcode.p1963;

import java.util.Stack;

// https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/description/?envType=daily-question&envId=2024-10-08
class Solution {
    public int minSwaps(String s) {
        char[] chs = s.toCharArray();
        int ans = 0;
        int stk = 0;
        for (char ch : chs) {
            if (ch == '[') {
                stk++;
            } else {
                if (stk == 0) {
                    ans++;
                    stk++;
                } else {
                    stk--;
                }
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
