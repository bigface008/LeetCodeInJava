package org.leetcode.p1928;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.cn/problems/minimum-cost-to-reach-destination-in-time/description/?envType=daily-question&envId=2024-10-03
//class Solution {
//    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
//        final int N = passingFees.length;
//        Map<Integer, Set<int[]>> graph = new HashMap<>(); // key: x * 10000 + t, value:
//        int[][] memo = new int[maxTime + 1][N];
//        for (int[] edge : edges) {
//            int x = edge[0], y = edge[1], time = edge[2];
//            graph.computeIfAbsent(x, key -> new HashSet<>()).add(new int[]{y, time});
//            graph.computeIfAbsent(y, key -> new HashSet<>()).add(new int[]{x, time});
//        }
//        memo[0][0] = 0;
//        for (int time = 1; time <= maxTime; time++) {
//            memo[time][0] = Integer.MAX_VALUE;
//        }
//        for (int node = 1; node < N; node++) {
//            memo[0][node] = Integer.MAX_VALUE;
//        }
//        for (int time = 1; time <= maxTime; time++) {
//            for (int node = 1; node < N; node++) {
//                Set<int[]> neighbors = graph.get(node);
//                if (neighbors == null) {
//                    continue;
//                }
//                int ans = Integer.MAX_VALUE;
//                for (int[] info : neighbors) {
//                    int p = info[0], t = info[1];
//                    if (time >= t) {
//                        ans = Math.min(ans, memo[time - t][p]);
//                    }
//                }
//                if (ans != Integer.MAX_VALUE) {
//                    ans += passingFees[node];
//                }
//                memo[time][node] = ans;
//            }
//        }
//        int ans = Integer.MAX_VALUE;
//        for (int time = 0; time <= maxTime; time++) {
//            ans = Math.min(ans, memo[time][N - 1]);
//        }
//        if (ans == Integer.MAX_VALUE) {
//            return -1;
//        }
//        return ans + passingFees[0];
//    }
//}

class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        final int N = passingFees.length;
        Map<Integer, Set<int[]>> graph = new HashMap<>(); // key: x * 10000 + t, value:
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], time = edge[2];
            graph.computeIfAbsent(x, key -> new HashSet<>()).add(new int[]{y, time});
            graph.computeIfAbsent(y, key -> new HashSet<>()).add(new int[]{x, time});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        }); // cost, point, time
        pq.offer(new int[]{passingFees[0], 0, 0});
        int[] times = new int[N];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[0] = 0;

        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int curCost = pair[0], curPoint = pair[1], curTime = pair[2];
            if (curPoint == N - 1) {
                return curCost;
            }
            Set<int[]> neighbors = graph.get(curPoint);
            if (neighbors == null) {
                continue;
            }
            for (int[] nbr : neighbors) {
                int p = nbr[0], t = nbr[1];
                int newTime = t + curTime;
                if (newTime <= maxTime && newTime < times[p]) {
                    times[p] = newTime;
                    int newCost = passingFees[p] + curCost;
                    pq.offer(new int[]{newCost, p, t + curTime});
                }
            }
        }
        return -1;
    }
}

//class Solution {
//    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
//        final int N = passingFees.length;
//        Map<Integer, Set<Integer>> graph = new HashMap<>(); // key: x * 10000 + t, value:
//        for (int[] edge : edges) {
//            int x = edge[0], y = edge[1], time = edge[2];
//            for (int t = 0; t + time <= maxTime; t++) {
//                int xKey1 = key(x, t), yKey1 = key(y, t + time);
//                int xKey2 = key(x, t + time), yKey2 = key(y, t);
//                graph.computeIfAbsent(xKey1, key -> new HashSet<>()).add(yKey1);
//                graph.computeIfAbsent(yKey2, key -> new HashSet<>()).add(xKey2);
//            }
//        }
//
//        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
//            return a[0] - b[0];
//        }); // cost, point
//        pq.offer(new int[]{0, 0});
//        Map<Integer, Integer> distances = new HashMap<>();
//        distances.put(0, 0);
//        int ans = Integer.MAX_VALUE;
//        while (!pq.isEmpty()) {
//            int[] pair = pq.poll();
//            int cost = pair[0], point = pair[1];
//            if (distances.getOrDefault(point, Integer.MAX_VALUE) < cost) {
//                continue;
//            }
//            if (nodeFromKey(point) == N - 1) {
//                ans = Math.min(ans, cost);
//            }
//            Set<Integer> neighbors = graph.get(point);
//            if (neighbors == null) {
//                continue;
//            }
//            for (int nbr : neighbors) {
//                int originNode = nodeFromKey(nbr);
//                int fee = passingFees[originNode];
//                int newDist = fee + cost;
//                if (newDist < distances.getOrDefault(nbr, Integer.MAX_VALUE)) {
//                    distances.put(nbr, newDist);
//                    pq.offer(new int[]{newDist, nbr});
//                }
//            }
//        }
//        if (ans == Integer.MAX_VALUE) {
//            return -1;
//        }
//        return ans + passingFees[0];
//    }
//
//    int key(int x, int t) {
//        return x * 10000 + t;
//    }
//
//    int timeFromKey(int k) {
//        return k % 10000;
//    }
//
//    int nodeFromKey(int k) {
//        return k / 10000;
//    }
//}

public class Main {
    public static void main(String[] args) {
        test(30, "[[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]]", new int[]{5, 1, 2, 20, 20, 3}, 11);
//        test(29, "[[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]]", new int[]{5, 1, 2, 20, 20, 3}, 48);
//        test(25, "[[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]]", new int[]{5, 1, 2, 20, 20, 3}, -1);
    }

    private static void test(int maxTime, String edgesStr, int[] passingFees, int expect) {
        try {
            int[][] edges = LeetCodeUtils.make2DIntArray(edgesStr);
            int output = new Solution().minCost(maxTime, edges, passingFees);
            String desc = String.format("min cost maxTime=%d edges=%s passingFees=%s", maxTime, edgesStr, Arrays.toString(passingFees));
            LeetCodeUtils.test(desc, output, expect);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
