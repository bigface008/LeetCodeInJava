package org.algo.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {
    static char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

    private static int INF = Integer.MAX_VALUE / 2;

    public static void dijkstra2(int matrix[][]) {
        boolean[] visited = new boolean[matrix.length];
        int[] dist = new int[matrix.length];
        visited[0] = true;
        int min_cur = 0;
        int min_value;

        //初始化
        for (int i = 0; i < dist.length; i++) {
            dist[i] = matrix[0][i];
        }

        for (int k = 0; k < dist.length; k++) {
            int temp = dist[min_cur];
            int temp_cur = min_cur;
            min_value = Integer.MAX_VALUE / 2;
            visited[min_cur] = true;

            System.out.println("加入了点： " + vexs[min_cur]);

            for (int i = 0; i < dist.length; i++) {
                if (!visited[i]) {
                    dist[i] = Math.min(dist[i], temp + matrix[temp_cur][i]);
                    if (dist[i] < min_value) {
                        min_value = dist[i];
                        min_cur = i;
                    }
                }
            }
        }
        System.out.println("======================");
        System.out.println(Arrays.toString(dist));
    }

    public static int[] dijkstra(int matrix[][]) {
        final int N = matrix.length;
        boolean[] visisted = new boolean[N];
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = matrix[0][i];
        }
        int minCurr = 0;
        for (int k = 0; k < N; k++) {
            int tempCurr = minCurr;
            int minDist = INF;
            visisted[minCurr] = true;
            for (int j = 0; j < N; j++) {
                if (visisted[j]) {
                    continue;
                }
                dist[j] = Math.min(dist[j], dist[tempCurr] + matrix[tempCurr][j]);
                if (dist[j] < minDist) {
                    minDist = dist[j];
                    minCurr = j;
                }
            }
        }
        return dist;
    }

    // 使用了堆
    public static int[] dijkstra3(int matrix[][], int startPoint) {
        final int N = matrix.length;
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[startPoint] = 0; // 记住，这种写法要求一开始起始节点为0,其余为INF。
        // (point, dist)
        var pq = new PriorityQueue<int[]>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{startPoint, 0});
        while (!pq.isEmpty()) {
            int[] group = pq.poll();
            int point = group[0], len = group[1];
            if (len > dist[point]) { // 记住这里
                continue;
            }
            int[] neighbors = matrix[point];
            for (int neighbor = 0; neighbor < N; neighbor++) { // search only neighbors 改成map更好
                int neighborDist = neighbors[neighbor];
                if (neighborDist == INF || neighbor == point) {
                    continue;
                }
                int newDist = len + neighborDist;
                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    pq.add(new int[]{neighbor, newDist});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int matrix[][] = {
                // [0, 12, 21, 22, 17, 15, 14]
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 15, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 5, INF, INF},
                /*E*/ {INF, INF, 5, 5, 0, 2, 8},
                /*F*/ {15, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        int[] dist = dijkstra3(matrix, 0);
        System.out.println(Arrays.toString(dist));

        // A B C D E F G
        // 0 1 2 3 4 5 6
        int[][] m2 = buildMatrix(7, new int[][]{
                // A B 12
                {0, 1, 12},
                // A F 16
                {0, 5, 16},
                // A G 14
                {0, 6, 14},
                // B F 7
                {1, 5, 7},
                // B C 10
                {1, 2, 10},
                // G F 9
                {6, 5, 9},
                // G E 8
                {6, 4, 8},
                // F C 6
                {5, 2, 6},
                // F E 2
                {5, 4, 2},
                // C E 5
                {2, 4, 5},
                // C D 3
                {2, 3, 3},
                // D E 4
                {3, 4, 4},
        });
        int[] dist2 = dijkstra3(m2, 3);
        // A(22) B(13) C(3) D(0) E(4) F(6) G(12)
        System.out.println(Arrays.toString(dist2));
    }

    private static int[][] buildMatrix(int N, int[][] edges) {
        int[][] ans = new int[N][N];
        for (int[] row : ans) {
            Arrays.fill(row, INF);
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], len = edge[2];
            ans[x][y] = len;
            ans[y][x] = len;
        }
        return ans;
    }
}
