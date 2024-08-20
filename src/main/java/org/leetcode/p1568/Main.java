package org.leetcode.p1568;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island/description/?envType=daily-question&envId=2024-08-11
class Solution {
    public int minDays(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        int i = 0, j = 0;
//        Map<Integer, List<int[]>> mp = new HashMap<>();
        List<int[]> onePairs = new ArrayList<>();
        for (; i < M; i++) {
            for (; j < N; j++) {
                if (grid[i][j] == 1) {
                    // count neighbor
                    onePairs.add(new int[]{i, j, countNeighbor(i, j, grid)});
                }
            }
        }
        if (onePairs.isEmpty()) {
            return 0;
        }
        onePairs.sort((a, b) -> a[2] - b[2]);
        int ans = 0;
        boolean[][] visited = new boolean[M][N];
        int[] pair = onePairs.get(0);
        while (isConnect(pair[0], pair[1], grid, visited)) {
            int r = pair[0], c = pair[1];
            int cnt = countNeighbor(r, c, grid);
            if (cnt == 0) {
                ans++;
                break;
            }
            ans += cnt;
        }
        return ans;
    }

    int countNeighbor(int r, int c, int[][] grid) {
        int ans = 0;
        if (r - 1 >= 0 && grid[r - 1][c] == 1) {
            ans++;
        }
        if (c - 1 >= 0 && grid[r][c - 1] == 1) {
            ans++;
        }
        if (r + 1 < grid.length && grid[r + 1][c] == 1) {
            ans++;
        }
        if (c + 1 < grid[0].length && grid[r][c + 1] == 1) {
            ans++;
        }
        return ans;
    }

    void clearVisited(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    boolean isConnect(int rStart, int cStart, int[][] grid, boolean[][] visited) {
        final int M = grid.length;
        final int N = grid[0].length;
        clearVisited(visited);
        dfs(rStart, cStart, grid, visited);
        int i = 0, j = 0;
        for (; i < M; i++) {
            for (; j < N; j++) {
                if (grid[i][j] == 1) {
                    break;
                }
            }
        }
        return false;
    }

    void dfs(int rStart, int cStart, int[][] grid, boolean[][] visited) {

    }
}

interface Gardener {
    default void check() {
        System.out.println("Gardener check default");
    }
}

interface Ranger {
    void range();
}

class MyClass implements Gardener, Ranger {
    public int justWe1 = 1;

    private final String justStr1 = "weeeeeee!";

    @Override
    public void check() {
//        Gardener.super.range();
        System.out.println("Oppps");
    }

    @Override
    public void range() {
        System.out.println("Range");
    }
}

public class Main {
    public static void main(String[] args) {
//        test("[[0,1,1,0],[0,1,1,0],[0,0,0,0]]", 2);
//        test("[[1,1]]", 2);
        MyClass c = new MyClass();
        c.check();
    }

    private static void test(String gridStr, int expect) {
        int[][] grid = LeetCodeUtils.make2DIntArray(gridStr);
        int output = new Solution().minDays(grid);
        String desc = String.format("test minDays grid=%s", gridStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
