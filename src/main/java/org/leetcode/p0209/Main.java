package org.leetcode.p0209;

import com.sun.source.tree.BreakTree;
import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-size-subarray-sum/
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        final int N = nums.length;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        int left = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            while (sum >= target) {
                ans = Math.min(ans, i - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        if (ans == Integer.MAX_VALUE) {
            return 0;
        }
        return ans;
    }
}

class Solution2 {
    public int minSubArrayLen(int target, int[] nums) {
        final int N = nums.length;
        int ans = -1;
        var dp = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            if (sum >= target) {
                ans = i + 1;
                break;
            }
        }
        if (ans == -1) {
            return 0;
        }
        dp[ans - 1] = ans;
        for (int i = ans; i < N; i++) {

        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(7, new int[]{2, 3, 1, 2, 4, 3}, 2);
        test(4, new int[]{1, 4, 4}, 1);
        test(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 0);
    }

    private static void test(int target, int[] nums, int expect) {
        Solution solution = new Solution();
        int output = solution.minSubArrayLen(target, nums);
        String desc = String.format("min sub array len target=%d nums=%s", target, Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
