package org.leetcode.p0650;

// n % 2 == 0
// f(n / 2) + 2
// n % 3 == 0
// f(n / 3) + 3
// n % 4 == 0
// f(n / 4) + 4

// https://leetcode.com/problems/2-keys-keyboard/?envType=daily-question&envId=2024-08-19
class Solution {
    int[] memo;

    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        memo = new int[n];
        return dfs(n);
    }

    int dfs(int n) {
        if (n == 1) {
            return 0;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        int ans = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                ans = Math.min(ans, dfs(n / i) + i);
            }
        }
        memo[n] = ans;
        return ans;
    }
}

public class Main {
}
