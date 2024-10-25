package org.leetcode.p2938;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/separate-black-and-white-balls/description/?envType=daily-question&envId=2024-10-15
class Solution {
    public long minimumSteps(String s) {
        final int N = s.length();
        long ans = 0;
        int zeroCnt = 0;
        for (int i = N - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '0') {
                zeroCnt++;
            } else {
                ans += zeroCnt;
            }
        }
        return ans;
    }
}


//class Solution {
//    public long minimumSteps(String s) {
//        final int N = s.length();
//        int[] values = new int[N];
//        for (int i = 0; i < N; i++) {
//            values[i] = s.charAt(i) - '0';
//        }
//        int pivot = values[0];
//        int l = 0, r = N - 1;
//        int ans = 0;
//        while (l < r) {
//            while (l < r && values[r] >= pivot) {
//                r--;
//            }
//            if (values[l] != values[r]) {
//                values[l] = values[r];
//                ans++;
//            }
//            while (l < r && values[l] <= pivot) {
//                l++;
//            }
//            if (values[r] != values[l]) {
//                values[r] = values[l];
//                ans++;
//            }
//        }
//        values[l] = pivot;
//        return ans;
//    }
//}

public class Main {
    public static void main(String[] args) {
//        test("0111", 0);
        test("100", 2);
    }

    private static void test(String s, long expect) {
        long output = new Solution().minimumSteps(s);
        String desc = String.format("s=%s", s);
        LeetCodeUtils.test(desc, output, expect);
    }
}
