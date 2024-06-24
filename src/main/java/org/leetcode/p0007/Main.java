package org.leetcode.p0007;

import org.leetcode.utils.LeetCodeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
class Solution {
    static String lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return "";
        }
        String ans = "";
        Map<Character, Integer> mp = new HashMap<>();
        int start = 0, i = 0;
        for (; i < s.length(); i++) {
            Character cc = s.charAt(i);
            if (mp.containsKey(cc)) {
                int index = mp.get(cc);
                if (index > start) {
                    int len = i - start + 1;
                    if (len > ans.length()) {
                        ans = s.substring(start, i);
                    }
                }
            }
            mp.put(cc, i);
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        LeetCodeUtils.test("just a test", 12, 15);
        LeetCodeUtils.test("just another test", "123", "123");
        LeetCodeUtils.test("just a container test", List.of("1", "12", "123"), List.of("1", "12", "123"));
//        LeetCodeUtils.test("just a test %s", 12, 15, "123");
    }
}
