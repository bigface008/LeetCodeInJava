package org.leetcode.p0962;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// https://leetcode.com/problems/maximum-width-ramp/

class Solution {
    public int maxWidthRamp(int[] nums) {
        final int N = nums.length;
        Stack<Integer> stk = new Stack<>();
        stk.add(N - 1);
        for (int i = N - 2; i >= 0; i--) {
            if (nums[stk.peek()] < nums[i]) {
                stk.add(i);
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                ans = Math.max(ans, stk.pop() - i);
            }
        }
        return ans;
    }
}

//class Solution {
//    public int maxWidthRamp(int[] nums) {
//        final int N = nums.length;
//        Stack<Integer> stk = new Stack<>();
//        stk.add(0);
//        for (int i = 1; i < N; i++) {
//            if (nums[i] < nums[stk.peek()]) {
//                stk.add(i);
//            }
//        }
//        int ans = 0;
//        for (int i = N - 1; i >= 0; i--) {
//            while (!stk.empty() && nums[i] >= nums[stk.peek()]) {
//                ans = Math.max(ans, i - stk.peek());
//                stk.pop();
//            }
//        }
//        return ans;
//    }
//}


//class Solution {
//    public int maxWidthRamp(int[] nums) {
//        final int N = nums.length;
//        int[] maxOnRight = new int[N];
//        maxOnRight[N - 1] = nums[N - 1];
//        for (int i = N - 2; i >= 0; i--) {
//            maxOnRight[i] = Math.max(maxOnRight[i + 1], nums[i]);
//        }
//        int ans = 0;
//        int left = 0;
//        for (int right = 0; right < N; right++) {
//            while (left <= right && maxOnRight[right] < nums[left]) {
//                left++;
//            }
//            ans = Math.max(ans, right - left);
//        }
//        return ans;
//    }
//}

//class Solution {
//    public int maxWidthRamp(int[] nums) {
//        int ans = 0;
//        for (int i = 1; i < nums.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (nums[j] <= nums[i]) {
//                    ans = Math.max(ans, i - j);
//                }
//            }
//        }
//        return ans;
//    }
//}

//class Solution {
//    public int maxWidthRamp(int[] nums) {
//        final int N = nums.length;
//        int[] dp = new int[N];
//        int ans = 0;
//        if (nums[1] >= nums[0]) {
//            dp[1] = 0;
//            ans = 1;
//        } else {
//            dp[1] = -1;
//        }
//        for (int i = 2; i < N; i++) {
//            int prev = dp[i - 1];
//            int x = nums[i];
//            if (prev == -1) {
//                if (x == nums[i - 1]) {
//                    dp[i] = i - 1;
//                    continue;
//                } else if (x < nums[i - 1]) {
//                    dp[i] = -1;
//                    continue;
//                }
//            } else {
//                if (x >= nums[i - 1]) {
//                    dp[i] = prev;
//                    continue;
//                }
//            }
//            boolean find = false;
//            for (int j = 0; j <= i - 1; j++) {
//                if (nums[j] <= x) {
//                    dp[i] = j;
//                    find = true;
//                    break;
//                }
//            }
//            if (!find) {
//                dp[i] = -1;
//            }
//        }
//        for (int i = 1; i < N; i++) {
//            if (dp[i] != -1) {
//                ans = Math.max(ans, i - dp[i]);
//            }
//        }
//        System.out.println(Arrays.toString(dp));
//        return ans;
//    }
//}

public class Main {
    public static void main(String[] args) {
//        test(new int[]{6, 0, 8, 2, 1, 5}, 4);
//        test(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}, 7);
        test(new int[]{3, 1, 2, 4}, 3);
    }

    static void test(int[] nums, int expect) {
        int output = new Solution().maxWidthRamp(nums);
        String desc = String.format("max width nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
