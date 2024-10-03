package org.leetcode.p1590;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/make-sum-divisible-by-p/description/?envType=daily-question&envId=2024-10-03
class Solution {
    public int minSubarray(int[] nums, int p) {
        final int N = nums.length;
        long[] preSum = new long[N + 1];
        Map<Long, Integer> map = new HashMap<>();
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }
        long totalMod = sum % p;
        if (totalMod == 0) {
            return 0;
        }
        int ans = N;
        for (int i = 0; i < N + 1; i++) {
            long preMod = preSum[i] % p;
            long anotherMod = (preMod - totalMod + p) % p;
            if (map.containsKey(anotherMod)) {
                ans = Math.min(ans, i - map.get(anotherMod)); // TODO
            }
            map.put(preMod, i);
        }
        if (ans == N) {
            return -1;
        }
        return ans;
    }
}

//class Solution {
//    public int minSubarray(int[] nums, int p) {
//        final int N = nums.length;
//        long[] preSum = new long[N + 1];
//        long sum = 0;
//        for (int i = 0; i < N; i++) {
//            sum += nums[i];
//            preSum[i + 1] = sum;
//        }
//        long totalMod = sum % p;
//        if (totalMod == 0) {
//            return 0;
//        }
//        int ans = Integer.MAX_VALUE;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                if ((preSum[j + 1] - preSum[i]) % p == totalMod) {
//                    ans = Math.min(ans, j - i + 1);
//                }
//            }
//        }
//        if (ans == Integer.MAX_VALUE || ans == N) {
//            return -1;
//        }
//        return ans;
//    }
//}

public class Main {
    public static void main(String[] args) {
        System.out.println();
    }

    private static void test(int[] nums, int p, int expect) {
        int output = new Solution().minSubarray(nums, p);

    }
}
