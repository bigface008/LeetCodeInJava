package org.leetcode.p3158;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/find-the-xor-of-numbers-which-appear-twice/
class Solution {
    public int duplicateNumbersXOR(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int x : nums) {
            int cnt = map.getOrDefault(x, 0) + 1;
            if (cnt == 2) {
                ans ^= x;
            }
            map.put(x, cnt);
        }
        return ans;
    }
}

public class Main {
}
