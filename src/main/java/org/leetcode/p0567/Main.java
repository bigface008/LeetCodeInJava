package org.leetcode.p0567;

import org.leetcode.utils.LeetCodeUtils;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/permutation-in-string/description
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        final int CH_CNT = 26;
        final int N1 = s1.length();
        final int N2 = s2.length();
        if (N2 < N1) {
            return false;
        }
        int[] counter1 = new int[CH_CNT];
        for (int i = 0; i < N1; i++) {
            counter1[s1.charAt(i) - 'a']++;
        }
        int[] counter2 = new int[CH_CNT];
        for (int i = 0; i < N1; i++) {
            counter2[s2.charAt(i) - 'a']++;
        }

        int left = 0, right = N1;
        for (; right <= N2; right++) {
            boolean valid = true;
            for (int i = 0; i < CH_CNT; i++) {
                if (counter1[i] != counter2[i]) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return true;
            }
            if (right == N2) {
                break;
            }
            counter2[s2.charAt(right) - 'a']++;
            counter2[s2.charAt(left) - 'a']--;
            left++;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        test("adc", "dcda", true);
        test("ab", "eidbaooo", true);
        test("ab", "eidboaoo", false);
    }

    static void test(String s1, String s2, boolean expect) {
        boolean output = new Solution().checkInclusion(s1, s2);
        String desc = String.format("check inclusion s1=%s s2=%s", s1, s2);
        LeetCodeUtils.test(desc, output, expect);
    }
}
