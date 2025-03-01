package org.leetcode.p2467;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/most-profitable-path-in-a-tree/?envType=daily-question&envId=2025-02-24
class Solution {
    int[] bobTime;
    int N = 0;
    List<List<Integer>> graph;
    int[] amount;
    int ans = Integer.MIN_VALUE;

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        this.amount = amount;
        N = edges.length + 1;
        graph = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        bobTime = new int[N];
        Arrays.fill(bobTime, -1);

        dfsBob(bob, -1, 0);
        dfsAlice(0, -1, 0, 0);
        return ans;
    }

    private boolean dfsBob(int node, int parent, int time) {
        if (node == 0) {
            bobTime[node] = time;
            return true;
        }
        for (int subNode : graph.get(node)) {
            if (subNode != parent && dfsBob(subNode, node, time + 1)) {
                bobTime[node] = time;
                return true;
            }
        }
        return false;
    }

    private void dfsAlice(int node, int parent, int time, int reward) {
        if (time < bobTime[node] || bobTime[node] == -1) {
            reward += amount[node];
        } else if (time == bobTime[node]) {
            reward += amount[node] / 2;
        }
        List<Integer> subNodes = graph.get(node);
        if (subNodes.size() == 1 && parent != -1) {
            ans = Math.max(ans, reward);
            return;
        }
        for (int subNode : subNodes) {
            if (subNode != parent) {
                dfsAlice(subNode, node, time + 1, reward);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        test(LeetCodeUtils.make2DIntArray("[[0,1],[1,2],[1,3],[3,4]]"), 3, new int[]{-2, 4, 2, -4, 6}, 6);
    }

    private static void test(int[][] edges, int bob, int[] amount, int expect) {
        int output = new Solution().mostProfitablePath(edges, bob, amount);
        String desc = String.format("edges=%s bob=%d amount=%s", Arrays.deepToString(edges), bob, Arrays.toString(amount));
        LeetCodeUtils.test(desc, output, expect);
    }
}
