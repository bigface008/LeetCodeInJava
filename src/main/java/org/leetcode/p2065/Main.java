package org.leetcode.p2065;

import org.leetcode.utils.LeetCodeUtils;

import java.util.HashMap;

// https://leetcode.com/problems/maximum-path-quality-of-a-graph
class Solution {
    int maxVal = 0;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        HashMap<Integer, HashMap<Integer, Integer>> edgesMap = new HashMap<>();
        for (int[] edge : edges) {
            if (!edgesMap.containsKey(edge[0])) {
                edgesMap.put(edge[0], new HashMap<>());
            }
            edgesMap.get(edge[0]).put(edge[1], edge[2]);
            if (!edgesMap.containsKey(edge[1])) {
                edgesMap.put(edge[1], new HashMap<>());
            }
            edgesMap.get(edge[1]).put(edge[0], edge[2]);
        }
        boolean[] used = new boolean[values.length];
        used[0] = true;
        dfs(0, values[0], 0, maxTime, values, edgesMap, used);
        return maxVal;
    }

    private void dfs(int currIdx, int currValSum, int currTimeSum, int maxTime, int[] values, HashMap<Integer, HashMap<Integer, Integer>> edgesMap, boolean[] used) {
        if (currTimeSum > maxTime) {
            return;
        }
        if (currIdx == 0) {
            maxVal = Math.max(maxVal, currValSum);
        }
        HashMap<Integer, Integer> edges = edgesMap.get(currIdx);
        if (edges == null) {
            return;
        }
        for (Integer neighbor : edges.keySet()) {
            int time = edges.get(neighbor);
            int newVal = currValSum;
            boolean needRevert = false;
            if (!used[neighbor]) {
                newVal += values[neighbor];
                used[neighbor] = true;
                needRevert = true;
            }
            dfs(neighbor, newVal, currTimeSum + time, maxTime, values, edgesMap, used);
            if (needRevert) {
                used[neighbor] = false;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

    }

    private static void test(int[] values, String edgesStr, int maxTime, int expect) {

    }
}
