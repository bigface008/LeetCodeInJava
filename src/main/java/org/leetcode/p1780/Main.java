package org.leetcode.p1780;

import org.leetcode.utils.LeetCodeUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/?envType=daily-question&envId=2025-03-04
class Solution {
    Set<Integer> used = new HashSet<>();

    public boolean checkPowersOfThree(int n) {
        while (n != 0) {
            if (n % 3 == 0 || n % 3 == 1) {
                n /= 3;
            } else {
                return false;
            }
        }
        return true;
    }
}

//class Solution {
//    boolean[] used;
//
//    public boolean checkPowersOfThree(int n) {
//        int level = 0;
//        int power = 1;
//        while (power < n) {
//            power *= 3;
//            level++;
//        }
//        if (power == n) {
//            return true;
//        }
//        used = new boolean[level];
//        return dfs(0, n);
//    }
//
//    boolean dfs(int i, int remain) {
//        if (remain < 0) {
//            return false;
//        }
//        if (i >= used.length && remain != 0) {
//            return false;
//        }
//        if (remain == 0) {
//            return true;
//        }
//        if (used[i]) {
//            return dfs(i + 1, remain);
//        }
//        int val = (int) Math.pow(3, i);
//        if (val == remain) {
//            return true;
//        } else if (val > remain) {
//            return dfs(i + 1, remain);
//        } else {
//            if (dfs(i + 1, remain)) {
//                return true;
//            }
//            used[i] = true;
//            boolean temp = dfs(i + 1, remain - val);
//            used[i] = false;
//            if (temp) {
//                return true;
//            }
//        }
//        return false;
//    }
//}

public class Main {
    public static void main(String[] args) {
        check(21, false);
//        check(12, true);
    }

    private static void check(int n, boolean expect) {
        Solution solution = new Solution();
        boolean actual = solution.checkPowersOfThree(n);
        String desc = String.format("n=%d", n);
        LeetCodeUtils.test(desc, actual, expect);
    }
}
