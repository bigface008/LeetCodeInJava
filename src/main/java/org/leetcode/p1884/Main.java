package org.leetcode.p1884;

// https://leetcode.cn/problems/egg-drop-with-2-eggs-and-n-floors/description/?envType=daily-question&envId=2024-10-13

// k0: 1 ~ k0 - 1 (1)                1
// k1: k0 + 1 ~ k1 - 1 (1 + k1 - k0) 9
// k2: k1 + 1 ~ k2 - 1 (2 + k2 - k1)
// k3: k2 + 1 ~ k3 - 1 (3 + k3 - k2)
// ...
// ki: (i + ki - ki-1)

// sumi = sum(1->i) + ki - k0 = n

// i*(1+i)/2 + ki = n

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

class Solution {
    public int twoEggDrop(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 2; i <= n; i++) {
            int temp = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                temp = Math.min(temp, Math.max(j, 1 + dp[i - j]));
            }
            dp[i] = temp;
        }
        return dp[n];
    }
}

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(12);
        pq.offer(5);
        pq.offer(-2);
        pq.offer(99);
        pq.offer(-23);
        System.out.println(pq);
    }

    private static void test(int n, int expect) {
        int output = new Solution().twoEggDrop(n);
        String desc = String.format("two egg drop n=%d", n);
        LeetCodeUtils.test(desc, output, expect);
    }

    static int[] solution(int[] nums) {
        final int N = nums.length;
        int[] ans = new int[N];
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int x = nums[i];
            List<Integer> indices = mp.get(x);
            if (indices != null) {
                for (int idx : indices) {
                    int temp = i - idx;
                    ans[idx] += temp;
                    ans[i] += temp;
                }
                indices.add(i);
            } else {
                mp.put(x, new LinkedList<>());
            }
        }
        return ans;
    }
}
