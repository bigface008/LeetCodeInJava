package org.algo.graph;

import java.util.*;

// https://pdai.tech/md/algorithm/alg-basic-graph-topo-sort.html
public class TopoSort {
    /**
     * Get topological ordering of the input directed graph
     *
     * @param n             number of nodes in the graph
     * @param edges adjacency list representation of the input directed graph
     * @return topological ordering of the graph stored in an List<Integer>.
     */
    // 这个就是kahn算法，BFS
    public static List<Integer> bfsSort(int n, int[][] edges) {
        List<Integer> topoRes = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
            graph.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
        }

        Deque<Integer> deque = new ArrayDeque<>();

        // start from nodes whose indegree are 0
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                deque.offer(i);
            }
        }

        while (!deque.isEmpty()) {
            int curr = deque.poll();
            topoRes.add(curr);
            List<Integer> children = graph.get(curr);
            if (children == null) {
                continue;
            }
            for (int child : children) {
                inDegree[child]--;
                if (inDegree[child] == 0) {
                    deque.offer(child);
                }
            }
        }

        return topoRes.size() == n ? topoRes : new ArrayList<>();
    }

    // dfs版本的拓扑排序
    // https://blog.csdn.net/Swofford/article/details/123884413
    public static List<Integer> dfsSort(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
        }
        List<Integer> topoRes = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, topoRes);
            }
        }
        return topoRes;
    }

    static void dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited, List<Integer> topoRes) {
        visited[node] = true;
        List<Integer> children = graph.get(node);
        if (children == null) {
            topoRes.add(node);
            return;
        }
        for (int child : children) {
            if (!visited[child]) {
                dfs(child, graph, visited, topoRes);
            }
        }
        topoRes.add(node);
    }

    public static void main(String[] args) {
        {
            int[][] edges = new int[][]{{4, 5}, {1, 2}, {3, 4}, {6, 7}, {8, 9}, {2, 3}, {7, 8}, {5, 6}, {0, 1}};
            List<Integer> res = TopoSort.bfsSort(10, edges);
            System.out.println(res);
        }
        {
            int[][] edges = new int[][]{{4, 5}, {1, 2}, {3, 4}, {6, 7}, {8, 9}, {2, 3}, {7, 8}, {5, 6}, {0, 1}};
            List<Integer> res = TopoSort.dfsSort(10, edges); // 注意是反的！
            System.out.println(res);
        }
    }
}
