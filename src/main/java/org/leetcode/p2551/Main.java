package org.leetcode.p2551;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.com/problems/put-marbles-in-bags/?envType=daily-question&envId=2025-03-31
class Solution {
    public long putMarbles(int[] weights, int k) {
        final int N = weights.length;
        if (k >= N || k == 1) {
            return 0;
        }
        List<Integer> allSums = new ArrayList<>(N - 1);
        for (int i = 0; i < N - 1; i++) {
            allSums.add(weights[i] + weights[i + 1]);
        }
        Collections.sort(allSums);
        long max = 0, min = 0;
        for (int i = 0; i < k - 1; i++) {
            min += allSums.get(i);
            max += allSums.get(allSums.size() - 1 - i);
        }
        return max - min;
    }
}


//class Solution {
//    public long putMarbles(int[] weights, int k) {
//        final int N = weights.length;
//        if (k >= N || k == 1) {
//            return 0;
//        }
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>((Integer a, Integer b) -> {
//            return weights[a] + weights[a + 1] - (weights[b] + weights[b + 1]);
//        });
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((Integer a, Integer b) -> {
//            return weights[b] + weights[b + 1] - (weights[a] + weights[a + 1]);
//        });
//        for (int i = 0; i < N - 1; i++) {
//            if (minHeap.size() < k - 1) {
//                minHeap.add(i);
//            } else {
//                int idx = minHeap.peek();
//                if (weights[idx] + weights[idx + 1] < weights[i] + weights[i + 1]) {
//                    minHeap.poll();
//                    minHeap.add(i);
//                }
//            }
//            if (maxHeap.size() < k - 1) {
//                maxHeap.add(i);
//            } else {
//                int idx = maxHeap.peek();
//                if (weights[idx] + weights[idx + 1] > weights[i] + weights[i + 1]) {
//                    maxHeap.poll();
//                    maxHeap.add(i);
//                }
//            }
//        }
//        long maxRes = weights[0] + weights[N - 1];
//        for (int i : minHeap) {
//            maxRes += weights[i] + weights[i + 1];
//        }
//        long minRes = weights[0] + weights[N - 1];
//        for (int i : maxHeap) {
//            minRes += weights[i] + weights[i + 1];
//        }
//        return maxRes - minRes;
//    }
//}

// 1 4 2 5 2
// 1, 4, 2 5 2 -> 13
// 1 4, 2 5, 2 -> 1+6+7+2=16
// 1, 4 2, 5 2 -> 1+5+7+2=15

public class Main {
    public static void main(String[] args) {
//        check(new int[]{1, 3, 5, 1}, 2, 4);
        check(new int[]{1, 4, 2, 5, 2}, 3, 3);
    }

    private static void check(int[] weights, int k, long expect) {
        long output = new Solution().putMarbles(weights, k);
        String desc = String.format("weights=%s k=%d", Arrays.toString(weights), k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
