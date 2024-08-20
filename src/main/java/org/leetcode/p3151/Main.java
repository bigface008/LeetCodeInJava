package org.leetcode.p3151;

// https://leetcode.cn/problems/special-array-i/description/?envType=daily-question&envId=2024-08-13
class Solution {
    public boolean isArraySpecial(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] % 2 == nums[i + 1] % 2) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
}
