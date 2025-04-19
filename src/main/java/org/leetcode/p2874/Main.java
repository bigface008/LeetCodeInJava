package org.leetcode.p2874;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-ii/?envType=daily-question&envId=2025-04-03
class Solution {
    public long maximumTripletValue(int[] nums) {
        final int N = nums.length;
        int[] prefixMax = new int[N];
        int[] prefixMin = new int[N];
        prefixMin[0] = nums[0];
        prefixMax[0] = nums[0];
        for (int i = 1; i < N; i++) {
            if (prefixMin[i - 1] <= nums[i]) {
                prefixMin[i] = prefixMin[i - 1];
            } else {
                prefixMin[i] = nums[i];
            }
            if (prefixMax[i - 1] >= nums[i]) {
                prefixMax[i] = prefixMax[i - 1];
            } else {
                prefixMax[i] = nums[i];
            }
        }
        int[] prefixMaxDiff = new int[N];
        for (int j = 1; j < N; j++) {
            prefixMaxDiff[j] = Math.max(prefixMaxDiff[j - 1], prefixMax[j - 1] - nums[j]);
        }
        long ans = 0;
        for (int k = 2; k < N; k++) {
            if (prefixMin[k - 1] == prefixMax[k - 1]) {
                continue;
            }
            ans = Math.max(ans, (long) prefixMaxDiff[k - 1] * (long) nums[k]);
        }
        return ans;
    }
}

class MyTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "42";
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new MyTask());
        System.out.println("Main thread running");

        String res = future.get();
        System.out.println("Res is " + res);
        executorService.shutdown();
//        check(new int[]{12, 6, 1, 2, 7}, 77);
//        check(new int[]{1, 10, 3, 4, 19}, 133);
//        check(new int[]{6, 11, 12, 12, 7, 9, 2, 11, 12, 4, 19, 14, 16, 8, 16}, 190);
    }

    static private void check(int[] nums, long expect) {
        long output = new Solution().maximumTripletValue(nums);
        String desc = String.format("nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
