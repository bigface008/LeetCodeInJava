package org.leetcode.p1192;

import java.util.*;

class Solution {
    int seq = 0;
    boolean[] visited;
    int[] dfn;
    int[] low;
    Map<Integer, Set<Integer>> graph;
    List<List<Integer>> ans;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        seq = 0;
        visited = new boolean[n];
        dfn = new int[n];
        low = new int[n];
        graph = new HashMap<>();
        for (List<Integer> conn : connections) {
            int x = conn.get(0), y = conn.get(1);
            graph.computeIfAbsent(x, key -> new HashSet<>()).add(y);
            graph.computeIfAbsent(y, key -> new HashSet<>()).add(x);
        }
        ans = new ArrayList<>();
        dfs(0, -1);
        return ans;
    }

    void dfs(int idx, int parentIdx) {
        visited[idx] = true;
        low[idx] = seq;
        dfn[idx] = seq;
        seq++;

        Set<Integer> neighbors = graph.get(idx);
        if (neighbors == null) {
            return;
        }
        for (int nbr : neighbors) {
            if (!visited[nbr]) {
                dfs(nbr, idx);
                low[idx] = Math.min(low[idx], low[nbr]);

                if (low[nbr] > dfn[idx]) {
                    ans.add(List.of(idx, nbr));
                }
            } else if (nbr != parentIdx) {
                low[idx] = Math.min(low[idx], dfn[nbr]);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
