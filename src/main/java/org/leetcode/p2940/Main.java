package org.leetcode.p2940;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// To start with, if we only have one query, we can directly use a mono stack, the bottom is the biggest one,
// the top is the smallest one. And we should iterate from right to left, thus we can tell the nearest larger
// element on the right.
// But now that we have multiple queries, we should optimize the solution a little bit.
// I think we can still iterate from right to left and keep maintaining the mono stack.
// At first, we should make a hashmap for the queries, the key should be the bigger index, the value should be the list
// of all corresponding queries.
// After that, just iterate from right to left. For each index, we can find if it exists in the map. If it does,
// get the corresponding queries. And for each query, we need to search in the mono stack to find the nearest and larger
// element. We can search in a binary way to lower the complexity.
// Emmm I think the main idea should be correct. Shall I begin coding now?


// https://leetcode.cn/problems/find-building-where-alice-and-bob-can-meet/description/
class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        Map<Integer, List<int[]>> bToA = new HashMap<>();
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int a = query[0], b = query[1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == b || heights[a] < heights[b]) {
                ans[i] = b;
            } else {
                bToA.computeIfAbsent(b, key -> new LinkedList<>()).add(new int[]{heights[a], i});
            }
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < heights.length; i++) {
            List<int[]> list = bToA.get(i);
            if (list != null) {
                minHeap.addAll(list);
            }
            int h = heights[i];
            while (!minHeap.isEmpty() && minHeap.peek()[0] < h) {
                ans[minHeap.poll()[1]] = i;
            }
        }
        return ans;
    }
}

// 离线+单调栈二分
class Solution4 {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        final int N = heights.length;
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        Map<Integer, List<int[]>> qs = new HashMap<>();
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int a = query[0], b = query[1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == b || heights[a] < heights[b]) {
                ans[i] = b;
            } else {
                qs.computeIfAbsent(b, key -> new LinkedList<>()).add(new int[]{heights[a], i});
            }
        }

        List<Integer> monoStk = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            int h = heights[i];
            List<int[]> list = qs.get(i);
            if (list != null) {
                for (int[] q : list) {
                    int ha = q[0], qi = q[1];
                    int res = bs(monoStk, ha, heights);
                    if (res != -1) {
                        ans[qi] = res;
                    }
                }
            }
            while (!monoStk.isEmpty() && heights[monoStk.get(monoStk.size() - 1)] <= h) {
                monoStk.remove(monoStk.size() - 1);
            }
            monoStk.add(i);
        }
        return ans;
    }

    // find last i, h[monoStk[i]] > ha
    int bs(List<Integer> monoStk, int ha, int[] heights) {
        int res = -1;
        int start = 0, end = monoStk.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int midVal = heights[monoStk.get(mid)];
            if (midVal > ha) {
                start = mid + 1;
                res = mid;
            } else {
                end = mid - 1;
            }
        }
        if (res == -1) {
            return -1;
        }
        return monoStk.get(res);
    }
}

class Solution3 {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        final int N = heights.length;
        int[] ans = new int[queries.length];
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            if (heights[i] < heights[N - 1]) {
                dp[i][N - 1] = N - 1;
            } else {
                dp[i][N - 1] = -1;
            }
        }
        for (int b = N - 2; b >= 0; b--) {
            for (int a = b; a >= 0; a--) {
                if (a == b || heights[b] > heights[a]) {
                    dp[a][b] = b;
                    continue;
                }
                if (heights[b + 1] > heights[a]) {
                    dp[a][b] = b + 1;
                } else {
                    dp[a][b] = dp[a][b + 1];
                }
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int a = query[0], b = query[1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            ans[i] = dp[a][b];
        }
        return ans;
    }
}


class Solution2 {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int a = query[0], b = query[1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (heights[a] < heights[b] || a == b) {
                ans[i] = b;
            } else {
                Stack<Integer> stk = new Stack<>();
                for (int j = heights.length - 1; j > b; j--) {
                    while (!stk.isEmpty() && heights[stk.peek()] < heights[j]) {
                        stk.pop();
                    }
                    stk.add(j);
                }
                // adding b
                int mh = Math.max(heights[b], heights[a]);
                while (!stk.isEmpty() && heights[stk.peek()] <= mh) {
                    stk.pop();
                }
                if (stk.isEmpty()) {
                    ans[i] = -1;
                } else {
                    ans[i] = stk.peek();
                }
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{6, 4, 8, 5, 2, 7}, "[[0,1],[0,3],[2,4],[3,4],[2,2]]", new int[]{2, 5, -1, 5, 2});
        test(new int[]{5, 3, 8, 2, 6, 1, 4, 6}, "[[0,7],[3,5],[5,2],[3,0],[1,6]]", new int[]{7, 6, -1, 4, 6});
        test(new int[]{1, 2, 1, 2, 1, 2}, "[[1,2]]", new int[]{-1});
    }

    private static void test(int[] heights, String queriesStr, int[] expect) {
        Solution solution = new Solution();
        int[][] queries = LeetCodeUtils.make2DIntArray(queriesStr);
        int[] result = solution.leftmostBuildingQueries(heights, queries);
        String desc = String.format("left most building heights=%s queries=%s", Arrays.toString(heights), queriesStr);
        LeetCodeUtils.test(desc, result, expect);
    }
}
