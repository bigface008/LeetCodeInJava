package org.leetcode.p0743;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/network-delay-time/
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] graph = new int[n][n];
        for (int[] row : graph) {
            Arrays.fill(row, INF);
        }
        for (int[] edge : times) {
            int u = edge[0], v = edge[1], cost = edge[2];
            graph[u - 1][v - 1] = cost;
        }

        int[] dist = new int[n];
        int maxDist = 0;
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        var pq = new PriorityQueue<int[]>(Comparator.comparingInt(o -> o[1])); // point,
        pq.add(new int[]{k - 1, 0});
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int point = info[0], len = info[1];
            if (len > dist[point]) {
                continue;
            }
            maxDist = Math.max(maxDist, len);
            int[] neighbors = graph[point];
            for (int neighbor = 0; neighbor < n; neighbor++) {
                int neighborDist = graph[point][neighbor];
                if (neighborDist == INF || point == neighbor) {
                    continue;
                }
                int newDist = len + neighborDist;
                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    pq.add(new int[]{neighbor, newDist});
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dist[i] == INF) {
                return -1;
            }
        }
        return maxDist;
    }
}

public class Main {
    public static void main(String[] args) {
    }
}
