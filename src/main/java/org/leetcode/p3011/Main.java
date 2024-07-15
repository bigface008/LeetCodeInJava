package org.leetcode.p3011;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.cn/problems/find-if-array-can-be-sorted/
class Solution {
    class Info {
        int bitCnt;
        int min;
        int max;

        Info(int bitCnt, int min, int max) {
            this.bitCnt = bitCnt;
            this.min = min;
            this.max = max;
        }
    }

    public boolean canSortArray(int[] nums) {
        int[] bits = countBits(Arrays.stream(nums).max().getAsInt());
        List<Info> infoList = new ArrayList<>();
        Info info = null;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int bitCnt = bits[num];
            if (info == null) {
                info = new Info(bitCnt, num, num);
            } else {
                if (bitCnt == info.bitCnt) {
                    info.min = Math.min(info.min, num);
                    info.max = Math.max(info.max, num);
                } else {
                    infoList.add(info);
                    info = new Info(bitCnt, num, num);
                }
            }
        }
        if (info != null) {
            infoList.add(info);
        }
        for (int i = 1; i < infoList.size(); i++) {
            Info curr = infoList.get(i);
            Info prev = infoList.get(i - 1);
            if (curr.min < prev.max) {
                return false;
            }
        }
        return true;
    }

    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        ans[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 0) {
                ans[i] = ans[i / 2];
            } else {
                ans[i] = ans[(i - 1) / 2] + 1;
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{8, 4, 2, 30, 15}, true);
        test(new int[]{1, 2, 3, 4, 5}, true);
        test(new int[]{3, 16, 8, 4, 2}, false);
    }

    private static void test(int[] nums, boolean expect) {
        Solution solution = new Solution();
        boolean output = solution.canSortArray(nums);
        String desc = String.format("can sort array nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
