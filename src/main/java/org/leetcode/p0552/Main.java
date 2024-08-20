package org.leetcode.p0552;

// dfs(i, P, na=na) = dfs(i-1, P, na=na) + dfs(i-1, L, na=na) + if na==1: dfs(i-1, A, na=1) else: 0
// dfs(i, L, na=na) = dfs(i-1, P, na=na) + dfs(i-1, ?) + if na==1: dfs(i-1, A, na=1) else: 0
// dfs(i, A) = dfs(i-1, P, na=0) + dfs(i-1, L, na=0)


// dfs(path,

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.List;

// dfs(i, na, ncl) = dfs(i-1, na, ncl) # P
// if na = 0: # A
//    + dfs(i-1, 1, ncl)
// else:
//    + 0
// if ncl <= 2: # L
//    + dfs(i-1, na, ncl+1)
// else:
//    + 0

// https://leetcode.cn/problems/student-attendance-record-ii/?envType=daily-question&envId=2024-08-19
class Solution3 {
    final static int MOD = (int) Math.pow(10, 9) + 7;
    int[][][] memo;

    public int checkRecord(int n) {
        memo = new int[n + 1][2][3];
        return dfs(n, 0, 0);
    }

    int dfs(int i, int j, int k) {
        if (i == 0) {
            return 1;
        }
        if (memo[i][j][k] > 0) {
            return memo[i][j][k];
        }
        int res = dfs(i - 1, j, 0); // P
        if (j == 0) { // A
            res += dfs(i - 1, 1, 0);
            res %= MOD;
        }
        if (k < 2) { // L
            res += dfs(i - 1, j, k + 1);
            res %= MOD;
        }
        res %= MOD;
        memo[i][j][k] = res;
        return res;
    }
}

class Solution {
    final static int MOD = (int) Math.pow(10, 9) + 7;

    public int checkRecord(int n) {
        int ans = 0;
        int[][][] f = new int[n + 1][2][3];
        for (int ncl = 2; ncl >= 0; ncl--) {
            for (int na = 1; na >= 0; na--) {
                f[0][na][ncl] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int k = 2; k >= 0; k--) {
                for (int j = 1; j >= 0; j--) {
                    int res = f[i][j][0];
                    if (j == 0) {
                        res += f[i][1][0];
                        res %= MOD;
                    }
                    if (k < 2) {
                        res += f[i][j][k + 1];
                        res %= MOD;
                    }
                    f[i + 1][j][k] = res % MOD;
                }
            }
        }
        return f[n][0][0] % MOD;
    }
}

class Solution2 {
    final static int MOD = (int) Math.pow(10, 9) + 7;
    int n;
    int ans = 0;
//    List<List<Character>> results;

    public int checkRecord(int n) {
        this.n = n;
        this.ans = 0;
//        this.results = new LinkedList<>();
        dfs(new ArrayList<>(n), 0);
//        System.out.println(results);
        return ans;
    }

    private void dfs(List<Character> path, int na) {
        if (path.size() == n) {
            ans++;
            ans %= MOD;
//            results.add(new ArrayList<>(path));
            return;
        }
        if (path.isEmpty()) {
            path.add('P');
            dfs(path, 0);
            path.set(0, 'L');
            dfs(path, 0);
            path.set(0, 'A');
            dfs(path, 1);
            return;
        }
        char lastCh = path.get(path.size() - 1);
        switch (lastCh) {
            case 'P':
                path.add('P');
                dfs(path, na);
                path.set(path.size() - 1, 'L');
                dfs(path, na);
                if (na == 0) {
                    path.set(path.size() - 1, 'A');
                    dfs(path, 1);
                }
                path.remove(path.size() - 1);
                break;
            case 'L':
                path.add('P');
                dfs(path, na);
                if (path.size() >= 3 && path.get(path.size() - 2) == 'L' && path.get(path.size() - 3) == 'L') {
                    // do nothing
                } else {
                    path.set(path.size() - 1, 'L');
                    dfs(path, na);
                }
                if (na == 0) {
                    path.set(path.size() - 1, 'A');
                    dfs(path, 1);
                }
                path.remove(path.size() - 1);
                break;
            default: // 'A'
                path.add('P');
                dfs(path, na);
                path.set(path.size() - 1, 'L');
                dfs(path, na);
                path.remove(path.size() - 1);
                break;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        test(2, 8);
        test(1, 3);
        test(10101, 183236316);
    }

    static void test(int n, int expect) {
        Solution solution = new Solution();
        int output = solution.checkRecord(n);
        String desc = String.format("check record n=%d", n);
        LeetCodeUtils.test(desc, output, expect);
    }
}
