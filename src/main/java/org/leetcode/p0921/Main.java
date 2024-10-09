package org.leetcode.p0921;

// https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/?envType=daily-question&envId=2024-10-09
class Solution {
    public int minAddToMakeValid(String s) {
        char[] chs = s.toCharArray();
        final int N = chs.length;
        int stk = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (chs[i] == '(') {
                stk++;
            } else {
                if (stk == 0) {
                    ans++;
                } else {
                    stk--;
                }
            }
        }
        ans += stk;
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
