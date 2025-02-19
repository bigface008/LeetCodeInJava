package org.leetcode.p0685;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    private int[] roots;
    private int[] ranks;
    private int N = 0;

    public int[] findRedundantDirectedConnection(int[][] edges) {
        N = 0;
        for (int[] edge : edges) {
            N = Math.max(Math.max(N, edge[0]), edge[1]);
        }
        int[] inDegrees = new int[N];
        for (int[] edge : edges) {
            inDegrees[edge[1] - 1]++;
        }
        List<Integer> candidates = new LinkedList<>();
        for (int i = edges.length - 1; i >= 0; i--) {
            if (inDegrees[edges[i][1] - 1] == 2) {
                candidates.add(i);
            }
            if (candidates.size() >= 2) {
                break;
            }
        }
        if (candidates.isEmpty()) {
            initUnionFind();
            for (int[] edge : edges) {
                int u = edge[0] - 1, v = edge[1] - 1;
                if (!connect(u, v)) {
                    return edge;
                }
            }
            return new int[]{};
        } else {
            if (isTreeAfterRemoval(edges, candidates.get(0))) {
                return edges[candidates.get(0)];
            } else if (isTreeAfterRemoval(edges, candidates.get(1))) {
                return edges[candidates.get(1)];
            } else {
                return new int[]{};
            }
        }
    }

    private boolean isTreeAfterRemoval(int[][] edges, int edgeIdx) {
        initUnionFind();
        for (int i = 0; i < edges.length; i++) {
            if (i == edgeIdx) {
                continue;
            }
            if (!connect(edges[i][0] - 1, edges[i][1] - 1)) {
                return false;
            }
        }
        return true;
    }

    private void initUnionFind() {
        roots = new int[N];
        ranks = new int[N];
        for (int i = 0; i < N; i++) {
            roots[i] = i;
        }
    }

    private int findRoot(int u) {
        while (roots[u] != u) {
            roots[u] = roots[roots[u]];
            u = roots[u];
        }
        return u;
    }

    private boolean connect(int u, int v) {
        int ur = findRoot(u);
        int vr = findRoot(v);
        if (ur == vr) {
            return false;
        }
        roots[vr] = ur;
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        test(LeetCodeUtils.make2DIntArray("[[2,1],[3,1],[4,2],[1,4]]"), new int[]{2, 1});
    }

    private static void test(int[][] edges, int[] expect) {
        int[] output = new Solution().findRedundantDirectedConnection(edges);
        String desc = String.format("find redundant directed connection in %s", Arrays.deepToString(edges));
        LeetCodeUtils.test(desc, output, expect);
    }
}
