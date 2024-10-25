package org.leetcode.p2530;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/maximal-score-after-applying-k-operations/
class Solution {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for (int x : nums) {
            pq.offer(x);
        }
        long ans = 0;
        while (k > 0 && !pq.isEmpty()) {
            int x = pq.poll();
            ans += x;
//            pq.offer((int) Math.ceil(x / 3));
            if (x % 3 != 0) {
                pq.offer(x / 3 + 1);
            } else {
                pq.offer(x / 3);
            }
            k--;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
//        test(new int[]{1, 10, 3, 3, 3}, 3, 17);
//        List<Integer> list = Arrays.asList(12, 2, 13, -2, 9);
//        Collections.reverse(list);
    }

    private static void test(int[] nums, int k, long expect) {
        long output = new Solution().maxKelements(nums, k);
        String desc = String.format("nums=%s k=%d", Arrays.toString(nums), k, expect);
        LeetCodeUtils.test(desc, output, expect);
    }
}
