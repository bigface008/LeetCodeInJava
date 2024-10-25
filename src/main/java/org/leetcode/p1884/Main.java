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
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        pq.offer(12);
//        pq.offer(5);
//        pq.offer(-2);
//        pq.offer(99);
//        pq.offer(-23);
//        System.out.println(pq);
//        test3(new int[]{1, 2, 1, 1}, new int[]{5, 0, 3, 4});
//        test3(new int[]{1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 1}, new int[]{30, 20, 17, 18, 16, 14, 16, 18, 17, 20, 30});
        test3(new int[]{1, 2, 1, 1});
        test3(new int[]{1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 1});
        test3(new int[]{1, 2, 1, 2, 2, 1, 1});
    }

    private static void test(int n, int expect) {
        int output = new Solution().twoEggDrop(n);
        String desc = String.format("two egg drop n=%d", n);
        LeetCodeUtils.test(desc, output, expect);
    }

    private static void test2(int[] nums, int[] expect) {
        int[] output = solution1(nums);
        String desc = String.format("nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }

    private static void test3(int[] nums) {
        int[] output1 = solution1(nums);
        int[] output2 = solution2(nums);
        if (Arrays.equals(output1, output2)) {
            System.out.print("[passed]");
        } else {
            System.out.print("[failed]");
        }
        System.out.printf(" nums=%s output1=%s output=%s\n", Arrays.toString(nums), Arrays.toString(output1), Arrays.toString(output2));
    }

    static int[] solution2(int[] nums) {
        final int N = nums.length;
        int[] ans = new int[N];
        Map<Integer, Queue<Integer>> mp = new HashMap<>();
        Map<Integer, Integer> sumMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int x = nums[i];
            Queue<Integer> indices = mp.get(x);
            if (indices == null) {
                indices = new LinkedList<>();
                indices.add(i);
                mp.put(x, indices);
                sumMap.put(x, 0);
            } else {
                indices.add(i);
                int sum = sumMap.get(x);
                sumMap.put(x, sum + i - indices.peek());
            }
        }
        mp.forEach((x, indices) -> {
            int idx = indices.poll();
            int sum = sumMap.get(x);
            int sumedIdxCnt = 1;
            final int indincesCnt = indices.size();
            ans[idx] = sum;
            while (!indices.isEmpty()) {
                int currIdx = indices.poll();
                System.out.printf("sumedIdxCnt=%d indincesCnt=%d ans[%d] = ans[%d] + %d * %d\n", sumedIdxCnt, indincesCnt, currIdx, idx, (sumedIdxCnt - 1) - (indincesCnt - sumedIdxCnt), currIdx - idx);
                ans[currIdx] = ans[idx] + ((sumedIdxCnt - 1) - (indincesCnt - sumedIdxCnt)) * (currIdx - idx);
                idx = currIdx;
                sumedIdxCnt++;
            }
        });
        return ans;
    }

    static int[] solution1(int[] nums) {
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
                indices = new LinkedList<>();
                indices.add(i);
                mp.put(x, indices);
            }
        }
        return ans;
    }
}
