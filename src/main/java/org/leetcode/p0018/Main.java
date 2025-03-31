package org.leetcode.p0018;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/4sum/description/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        final int N = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = 0; a < N - 3; a++) {
            long x = nums[a];
            if (a > 0 && nums[a - 1] == x) {
                continue;
            }
            if (x + nums[a + 1] + nums[a + 2] + nums[a + 3] > target) {
                break;
            }
            if (x + nums[N - 3] + nums[N - 2] + nums[N - 1] < target) {
                continue;
            }
            for (int b = a + 1; b < N - 2; b++) {
                long y = nums[b];
                if (b > a + 1 && nums[b - 1] == y) {
                    continue;
                }
                if (x + y + nums[b + 1] + nums[b + 2] > target) {
                    break;
                }
                if (x + y + nums[N - 2] + nums[N - 1] < target) {
                    continue;
                }
                int c = b + 1, d = N - 1;
                while (c < d) {
                    long currSum = x + y + nums[c] + nums[d];
                    if (currSum == target) {
                        ans.add(List.of((int) x, (int) y, nums[c], nums[d]));
                        c++;
                        d--;
                        while (c < d && nums[c] == nums[c - 1]) {
                            c++;
                        }
                        while (c < d && nums[d] == nums[d + 1]) {
                            d--;
                        }
                    } else if (currSum > target) {
                        d--;
                    } else {
                        c++;
                    }
                }
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 0, -1, 0, -2, 2}, 0, "[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]");
        test(new int[]{2, 2, 2, 2, 2}, 8, "[[2,2,2,2]]");
    }

    private static void test(int[] nums, int target, String expect) {
        String desc = String.format("nums=%s target=%d", Arrays.toString(nums), target);
        Solution sol = new Solution();
        var output = sol.fourSum(nums, target);
        LeetCodeUtils.test(desc, output, LeetCodeUtils.make2DIntList(expect));
    }
}
