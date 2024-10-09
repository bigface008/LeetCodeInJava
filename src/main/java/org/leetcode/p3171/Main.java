package org.leetcode.p3171;

// https://leetcode.cn/problems/find-subarray-with-bitwise-or-closest-to-k/?envType=daily-question&envId=2024-10-09

// 101...1 n
// 1
// 0

// a | b = c
// a | b | b = c | b
// a | b = c | b

import java.util.Arrays;

class Solution {
    public int minimumDifference(int[] nums, int k) {
        final int N = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int x = nums[i];
            ans = Math.min(ans, Math.abs(x - k));
            for (int j = i - 1; j >= 0; j--) {
                if ((nums[j] | x) == nums[j]) {
                    break;
                }
                nums[j] |= x;
                ans = Math.min(ans, Math.abs(nums[j] - k));
            }
        }
        return ans;
    }
}

// gcd

class GCD {
    int solution(int[] nums, int k) {
        final int N = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int x = nums[i];
            ans = Math.min(ans, Math.abs(x - k));
            for (int j = i - 1; j >= 0; j--) {
                int temp = gcd(nums[j], x);
                if (temp == nums[j]) {
                    break;
                }
                nums[j] = gcd(nums[j], x);
                ans = Math.min(ans, Math.abs(x - k));
            }
        }
        return ans;
    }

    int gcd(int a, int b) {
//        int a1 = a, b1 = b;
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
//        System.out.printf("gcd(%d, %d)=%d\n", a1, b1, a);
        return a;
    }
}

//class Solution {
//    int[] nums;
//    int k;
//    static final int K_LEN = (int) Math.ceil(Math.log(Math.pow(10, 9)) / Math.log(2));
//    int[] dp;
////    int ans;
//
//    public int minimumDifference(int[] nums, int k) {
//        this.nums = nums;
//        this.k = k;
////        this.dp = new int[nums.length];
////        int ans = -1;
////        Arrays.fill(dp, -1);
//        final int N = nums.length;
////        int[] dp = new int[N];
//        int ans = nums[0];
//        for (int i = 1; i < N; i++) {
//            int bestOrVal = 0;
//            int curr = nums[i];
//            if (curr >= k) {
//                bestOrVal = curr;
//            } else {
//                int val = curr;
//                bestOrVal = curr;
//                int bestDist = k - curr;
//                for (int j = i - 1; j >= 0; j--) {
//                    val |= nums[j];
//                    int d = Math.abs(val - k);
//                    if (d < bestDist) {
//                        bestDist = d;
//                        bestOrVal = val;
//                    }
//                    if (val >= k) {
//                        break;
//                    }
//                }
////                dp[i] = bestOrVal;
//            }
//            if (Math.abs(ans - k) > Math.abs(bestOrVal - k)) {
//                ans = bestOrVal;
//            }
//        }
//        return Math.abs(ans - k);
//    }
//
//    // prev = dfs(i-1)
//    // x = nums[i]
//    // if x >= k:
//    //   return x
//    // if x | prev closer than prev:
//    //   return x | prev
//    // else:
//    //
//    int dfs(int i) {
//        if (dp[i] != -1) {
//            return dp[i];
//        }
//        if (i == 0) {
//            return nums[0];
//        }
//        int curr = nums[i];
//        if (curr >= k) {
//            return curr;
//        }
//        int val = curr;
//        int bestOrVal = curr;
//        int bestDist = k - curr;
//        for (int j = i - 1; j >= 0; j--) {
//            val |= nums[j];
//            int d = Math.abs(val - k);
//            if (d < bestDist) {
//                bestDist = d;
//                bestOrVal = val;
//            }
//            if (val >= k) {
//                break;
//            }
//        }
//        return bestOrVal;
//    }
//}

public class Main {
    public static void main(String[] args) {
//        final int K_LEN = (int) Math.ceil(Math.log(Math.pow(10, 9)) / Math.log(2));
//        System.out.println(K_LEN);
        new GCD().solution(new int[]{}, 0);
    }
}
