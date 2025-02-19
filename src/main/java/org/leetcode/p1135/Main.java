package org.leetcode.p1135;

import java.util.Arrays;

// https://leetcode.cn/problems/connecting-cities-with-minimum-cost/
class Solution {
    int[] parents;
    int[] ranks;

    public int minimumCost(int n, int[][] connections) {
        Arrays.sort(connections, (a, b) -> {
            return a[2] - b[2];
        });
        parents = new int[n];
        ranks = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        int res = 0;
        int usedEdgesCnt = 0;
        for (int[] conn : connections) {
            int u = conn[0] - 1, v = conn[1] - 1, w = conn[2];
            int pu = findParent(u);
            int pv = findParent(v);
            if (pu == pv) {
                continue;
            }
            if (ranks[pu] > ranks[pv]) {
                parents[pv] = pu;
            } else if (ranks[pu] < ranks[pv]) {
                parents[pu] = pv;
            } else {
                parents[pv] = pu;
                ranks[pu]++;
            }
            res += w;
            usedEdgesCnt++;
            if (usedEdgesCnt == n - 1) {
                break;
            }
        }
        if (usedEdgesCnt == n - 1) {
            return res;
        } else {
            return -1;
        }
    }

    private int findParent(int u) {
        while (parents[u] != u) {
            parents[u] = parents[parents[u]];
            u = parents[u];
        }
        return u;
    }
}

public class Main {
}
