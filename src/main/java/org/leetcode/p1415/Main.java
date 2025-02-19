package org.leetcode.p1415;


// i  i+1
// a  b,c
// b  a,c
// c  a,b


// 1 a,b,c 3
// 2 ab,ac,ba,bc,ca,cb 6
// 3 aba,abc,aca,acb,...  12


// 1 << (n - 1)

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/?envType=daily-question&envId=2025-02-19
class Solution {
    char[] res;
    int n;

    public String getHappyString(int n, int k) {
        k--;
        final int per = 1 << (n - 1);
        final int limit = 3 * per;
        if (k >= limit) {
            return "";
        }
        res = new char[n];
        this.n = n;
        dfs(0, k, 'a');
        return new String(res);
    }

    void dfs(int resIdx, int k, char prevCh) {
        if (resIdx == n) {
            return;
        }
        if (resIdx == 0) {
            int per = 1 << (n - 1);
            int num = k / per;
            int remain = k % per;
            res[0] = (char) ('a' + num);
            dfs(1, remain, res[0]);
        } else {
            int per = 1 << (n - resIdx - 1);
            int num = k / per;
            int remain = k % per;
            char ch;
            switch (prevCh) {
                case 'a': {
                    ch = (num == 0) ? 'b' : 'c';
                    break;
                }
                case 'b': {
                    ch = (num == 0) ? 'a' : 'c';
                    break;
                }
                default: {
                    ch = (num == 0) ? 'a' : 'b';
                    break;
                }
            }
            res[resIdx] = ch;
            dfs(resIdx + 1, remain, ch);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        test(3, 9, "cab");
//        test(10, 100, "abacbabacb");
    }

    private static void test(int n, int k, String expect) {
        String output = new Solution().getHappyString(n, k);
        LeetCodeUtils.test(String.format("n=%d k=%d", n, k), output, expect);
    }
}
