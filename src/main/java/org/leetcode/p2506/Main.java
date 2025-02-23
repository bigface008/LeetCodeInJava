package org.leetcode.p2506;

import java.util.*;

// https://leetcode.cn/problems/count-pairs-of-similar-strings/?envType=daily-question&envId=2025-02-22
class Solution {
    public int similarPairs(String[] words) {
        Map<Integer, List<Integer>> posMap = new HashMap<>();
        final int N = words.length;
        for (int i = 0; i < N; i++) {
            int key = toKey(words[i]);
            posMap.computeIfAbsent(key, k -> new ArrayList<>()).add(i);
        }
        int ans = 0;
        for (var pair : posMap.entrySet()) {
            int num = pair.getValue().size();
            if (num > 1) {
                ans += num * (num - 1) / 2;
            }
        }
        return ans;
    }

    int toKey(String word) {
        int key = 0;
        for (char ch : word.toCharArray()) {
            key |= 1 << (ch - 'a');
        }
        return key;
    }
}

public class Main {
}
