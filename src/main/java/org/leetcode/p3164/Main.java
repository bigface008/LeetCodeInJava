package org.leetcode.p3164;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/find-the-number-of-good-pairs-ii/?envType=daily-question&envId=2024-10-11
class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> counter1 = new HashMap<>();
        int max1 = 0;
        for (int x : nums1) {
            counter1.put(x, counter1.getOrDefault(x, 0) + 1);
            max1 = Math.max(x, max1);
        }
        Map<Integer, Integer> counter2 = new HashMap<>();
        for (int x : nums2) {
            counter2.put(x, counter2.getOrDefault(x, 0) + 1);
        }
        long ans = 0;
        for (Map.Entry<Integer, Integer> entry : counter2.entrySet()) {
            int v2 = entry.getKey(), cnt = entry.getValue();
            for (int v1 = v2 * k; v1 <= max1; v1 += v2 * k) {
                if (counter1.containsKey(v1)) {
                    ans += (long) counter1.get(v1) * cnt;
                }
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 3, 4}, new int[]{1, 3, 4}, 1, 5);
    }

    static void test(int[] nums1, int[] nums2, int k, long expect) {
        long output = new Solution().numberOfPairs(nums1, nums2, k);
        String desc = String.format("nums1=%s nums2=%s k=%d", Arrays.toString(nums1), Arrays.toString(nums2), k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
