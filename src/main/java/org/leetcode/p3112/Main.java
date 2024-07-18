package org.leetcode.p3112;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.cn/problems/minimum-time-to-visit-disappearing-nodes
class Solution2 {
    static final int INF = Integer.MAX_VALUE / 2;

    static class Node {
        int point;
        int dist;

        Node(int point, int dist) {
            this.point = point;
            this.dist = dist;
        }
    }

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        int[][] graph = new int[n][n];
        for (int[] row : graph) {
            Arrays.fill(row, INF);
        }
        for (int i = 0; i < n; i++) {
            graph[i][i] = 0;
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], len = edge[2];
            graph[x][y] = Math.min(graph[x][y], len);
            graph[y][x] = graph[x][y];
        }

        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, INF);

        var pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.dist - o2.dist;
            }
        });
        pq.add(new Node(0, 0));
        while (!pq.isEmpty()) {
            var node = pq.poll();
            int bian = node.point;
            if (visited[bian]) {
                continue;
            }
            visited[bian] = true;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && graph[bian][i] + node.dist < dist[i]) {
                    dist[i] = graph[bian][i] + node.dist;
                    pq.add(new Node(i, dist[i]));
                }
            }
        }
        // 这个方法其实没写完，因为发现内存不足的问题，这个方法解决不了（
        return dist;
    }
}

class Solution {
    static final int INF = Integer.MAX_VALUE / 2;

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], len = edge[2];
            if (x > y) {
                int temp = x;
                x = y;
                y = temp;
            }
            if (graph.containsKey(x)) {
                graph.get(x).compute(y, (key, value) -> (value == null || value > len) ? len : value);
            } else {
                Map<Integer, Integer> mp = new HashMap<>();
                mp.put(y, len);
                graph.put(x, mp);
            }
        }

        int minCurr = 0;
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = getEdge(0, i, graph);
        }
        for (int k = 0; k < n; k++) {
            int tempCurr = minCurr;
            int minDist = INF;
            visited[minCurr] = true;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    continue;
                }
                dist[i] = Math.min(dist[i], dist[tempCurr] + getEdge(tempCurr, i, graph));
                if (dist[i] < minDist && dist[i] < disappear[i]) {
                    minCurr = i;
                    minDist = dist[i];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dist[i] >= disappear[i]) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    int getEdge(int i, int j, Map<Integer, Map<Integer, Integer>> graph) {
        if (i == j) {
            return 0;
        }
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }
        if (graph.containsKey(i)) {
            return graph.get(i).getOrDefault(j, INF);
        } else {
            return INF;
        }
    }
}

// 最终解决方法
class Solution3 {
    static final int INF = Integer.MAX_VALUE / 2;

    static class Node {
        int point;
        int dist;

        Node(int point, int dist) {
            this.point = point;
            this.dist = dist;
        }
    }

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        var graph = buildEdgeGraph(edges);
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        var pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.dist - o2.dist;
            }
        });
        pq.add(new Node(0, 0));
        while (!pq.isEmpty()) {
            var node = pq.poll();
            if (node.dist > dist[node.point]) {
                continue;
            }
            var neighbors = graph.get(node.point);
            if (neighbors == null) {
                continue;
            }
            neighbors.forEach((neighbor, len) -> {
                int newDist = node.dist + len;
                if (newDist < disappear[neighbor] && (dist[neighbor] < 0 || newDist < dist[neighbor])) {
                    dist[neighbor] = newDist;
                    pq.add(new Node(neighbor, newDist));
                }
            });
        }
        return dist;
    }

    Map<Integer, Map<Integer, Integer>> buildEdgeGraph(int[][] edges) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], len = edge[2];
            graph.computeIfAbsent(x, key -> new HashMap<>()).compute(y, (key, value) -> (value == null || value > len) ? len : value);
            graph.computeIfAbsent(y, key -> new HashMap<>()).compute(x, (key, value) -> (value == null || value > len) ? len : value);
        }
        return graph;
    }
}

public class Main {
    public static void main(String[] args) {
        test(3, "[[0,1,2],[1,2,1],[0,2,4]]", new int[]{1, 1, 5}, new int[]{0, -1, 4});
        test(3, "[[0,1,2],[1,2,1],[0,2,4]]", new int[]{1, 3, 5}, new int[]{0, 2, 3});
        test(2, "[[0,1,1]]", new int[]{1, 1}, new int[]{0, -1});
    }

    private static void test(int n, String edgesStr, int[] disappear, int[] expect) {
        var solution = new Solution3();
        int[][] edges = LeetCodeUtils.make2DIntArray(edgesStr);
        int[] output = solution.minimumTime(n, edges, disappear);
        var desc = String.format("minimum time n=%d edges=%s disappear=%s", n, edgesStr, Arrays.toString(disappear));
        LeetCodeUtils.test(desc, output, expect);
    }
}
