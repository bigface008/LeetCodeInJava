package org.leetcode.p2375;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/construct-smallest-number-from-di-string/?envType=daily-question&envId=2025-02-18
//class Solution {
//    public String smallestNumber(String pattern) {
//        final int N = pattern.length();
//        char[] chs = pattern.toCharArray();
//        char[] res = new char[N + 1];
//        int i = 0;
//        int currNum = 1;
//        int idxInRes = 0;
//        while (i < N) {
//            int start = i;
//            while (i < N && chs[i] == 'I') {
//                i++;
//            }
//            int mid = i;
//            while (i < N && chs[i] == 'D') {
//                i++;
//            }
//            int end = i;
//            for (int j = start; j < mid; j++) {
////                res[idxInRes] = (char) (currNum + j - start + '1');
//                res[idxInRes] = (char) (currNum - 1 + '1');
//                idxInRes++;
//                currNum++;
//            }
//            currNum += end - mid;
//            int temp = currNum;
//            for (int j = mid; j < end; j++) {
////                res[idxInRes] = (char) (currNum + end - start - (j - mid) + '1');
//                res[idxInRes] = (char) (currNum - 1 + '1');
//                idxInRes++;
//                currNum--;
//            }
//            res[idxInRes] = (char) (currNum - 1 + '1');
//            idxInRes++;
//            currNum = temp + 1;
//        }
//        return new String(res);
//    }
//}
//   s0          m3  e4
//   I   I   I   D   I   D   D   D
// 1   2   3   5   4   9   8   7   6

class Solution {
    public String smallestNumber(String pattern) {
        final int N = pattern.length();
        char[] res = new char[N + 1];
        int i = 0;
        int idxInRes = 0;
        char curr = '1';
        while (i < N) {
            if (i > 0 && pattern.charAt(i) == 'I') {
                i++;
            }
            for (; i < N && pattern.charAt(i) == 'I'; i++) {
                res[i] = curr;
                curr++;
            }
            int i0 = i;
            while (i < N && pattern.charAt(i) == 'D') {
                i++;
            }
            for (int j = i; j >= i0; j--) {
                res[j] = curr;
                curr++;
            }
        }
        return new String(res);
    }
}

public class Main {
    public static void main(String[] args) {
        test("IIIDIDDD", "123549876");
    }

    private static void test(String pattern, String expect) {
        try {
            String output = new Solution().smallestNumber(pattern);
            String desc = String.format("pattern=%s", pattern);
            LeetCodeUtils.test(desc, output, expect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
