package org.leetcode.p1497;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// To start with, this problem may have a solution similar with the two sum problem.
// We just need to generate a map. The key should be each element mod k, the value is the corresponding count.
// After we get the map, we can simply check if the value of each key is the same as the value of the remainder.
// Besides we need to make sure the count of key 0 should be even. Otherwise, we cannot group them into pairs.

// https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/description/?envType=daily-question&envId=2024-10-01
class Solution {
    public boolean canArrange(int[] arr, int k) {
        int[] freq = new int[k];
        for (int x : arr) {
            freq[((x % k) + k) % k]++;
        }
        if (freq[0] % 2 != 0) {
            return false;
        }
        for (int i = 1; i <= k / 2; i++) {
            if (freq[i] != freq[k - i]) {
                return false;
            }
        }
        return true;
//        Map<Integer, Integer> map = new HashMap<>();
//        final int N = arr.length;
//        for (int i = 0; i < N; i++) {
//            int val = arr[i];
//            if (arr[i] >= 0) {
//                val %= k;
//            } else {
//                val = (val + (-(val / k) + 1) * k) % k;
//            }
//            int remain = (k - val) % k;
//            if (map.containsKey(val)) {
//                int cnt = map.get(val);
//                if (cnt - 1 == 0) {
//                    map.remove(val);
//                } else {
//                    map.put(val, cnt - 1);
//                }
//            } else {
//                map.put(remain, map.getOrDefault(remain, 0) + 1);
//            }
//        }
//        return map.isEmpty();
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4, 5, 10, 6, 7, 8, 9}, 5, true);
    }

    private static void test(int[] arr, int k, boolean expect) {
        boolean output = new Solution().canArrange(arr, k);
        String desc = String.format("can arrange arr=%s k=%d", Arrays.toString(arr), k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
