package org.leetcode.p0632;

import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/?envType=daily-question&envId=2024-10-13
class Solution {
    static int INF = Integer.MAX_VALUE / 2;

    public int[] smallestRange(List<List<Integer>> nums) {
        final int N = nums.size();
        // arr idx, pos in arr, val
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });
        int max = -INF;
        for (int i = 0; i < N; i++) {
            int x = nums.get(i).get(0);
            pq.offer(new int[]{i, 0, x});
            max = Math.max(max, x);
        }
        int start = -INF, end = INF;
        while (pq.size() == N) {
            int[] node = pq.poll();
            int i = node[0], j = node[1], val = node[2];
            if (max - val < end - start) {
                start = val;
                end = max;
            }
            if (j + 1 < nums.get(i).size()) {
                int newVal = nums.get(i).get(j + 1);
                pq.offer(new int[]{i, j + 1, newVal});
                max = Math.max(max, newVal);
            }
        }
        return new int[]{start, end};
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
