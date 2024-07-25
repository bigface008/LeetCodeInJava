package org.leetcode.p2101;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

// https://leetcode.cn/problems/detonate-the-maximum-bombs/description/?envType=daily-question&envId=2024-07-22
// dfs
class Solution {
    public int maximumDetonation(int[][] bombs) {
        final int N = bombs.length;
        List<Integer>[] graph = new ArrayList[N];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int i = 0; i < N; i++) {
            int[] info = bombs[i];
            long x = info[0], y = info[1], r = info[2];
            for (int j = 0; j < N; j++) {
                long dx = x - bombs[j][0];
                long dy = y - bombs[j][1];
                if (dx * dx + dy * dy <= r * r) {
                    graph[i].add(j);
                }
            }
        }
        boolean[] visited = new boolean[N];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited, false);
            ans = Math.max(ans, dfs(i, visited, graph));
        }
        return ans;
    }

    int dfs(int i, boolean[] visited, List<Integer>[] graph) {
        visited[i] = true;
        List<Integer> neighbors = graph[i];
        int cnt = 1;
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                cnt += dfs(neighbor, visited, graph);
            }
        }
        return cnt;
    }
}

// floyd + bitset
class SolutionV2 {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        BitSet[] f = new BitSet[n];
        for (int i = 0; i < n; i++) {
            long x = bombs[i][0], y = bombs[i][1], r = bombs[i][2];
            f[i] = new BitSet(n);
            for (int j = 0; j < n; j++) {
                long dx = x - bombs[j][0];
                long dy = y - bombs[j][1];
                if (dx * dx + dy * dy <= r * r) {
                    f[i].set(j); // i 可以到达 j
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (BitSet fi : f) {
                if (fi.get(k)) { // i 可以到达 k
                    fi.or(f[k]); // i 也可以到 k 可以到达的点
                }
            }
        }

        int ans = 0;
        for (BitSet s : f) {
            ans = Math.max(ans, s.cardinality()); // 集合大小的最大值
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test("[[2,1,3],[6,1,4]]", 2);
        test("[[1,1,5],[10,10,5]]", 1);
        test("[[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]", 5);
    }

    private static void test(String bombsStr, int expect) {
        Solution solution = new Solution();
        int[][] bombs = LeetCodeUtils.make2DIntArray(bombsStr);
        int output = solution.maximumDetonation(bombs);
        String desc = String.format("maximum detonation %s", bombsStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
