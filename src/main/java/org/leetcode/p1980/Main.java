package org.leetcode.p1980;

import java.util.*;

// https://leetcode.com/problems/find-unique-binary-string/?envType=daily-question&envId=2025-02-20
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        final int N = nums.length;
        char[] ans = new char[N];
        for (int i = 0; i < N; i++) {
            if (nums[i].charAt(i) == '0') {
                ans[i] = '1';
            } else {
                ans[i] = '0';
            }
        }
        return new String(ans);
    }
}


class Solution2 {
    public String findDifferentBinaryString(String[] nums) {
        final int N = nums.length;
        final int M = nums[0].length();
        Set<Integer> set = new HashSet<>();
        for (String num : nums) {
            int val = 0;
            for (int i = M - 1; i >= 0; i--) {
                if (num.charAt(i) == '1') {
                    val += 1 << (M - 1 - i);
                }
            }
            set.add(val);
        }
        int res = -1;
        final int limit = 1 << (M - 1);
        for (int i = 0; i <= limit; i++) {
            if (!set.contains(i)) {
                res = i;
                break;
            }
        }
        if (res == -1) {
            return "";
        }
        char[] ans = new char[M];
        for (int i = 0; i < M; i++) {
            ans[M - 1 - i] = (char) ('0' + (res & 1));
            res >>= 1;
        }
        return new String(ans);
    }
}

public class Main {
}
