package org.leetcode.p2698;

import org.leetcode.utils.LeetCodeUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    static int[] PRE_SUM = new int[1001];

    static {
        for (int i = 1; i <= 1000; i++) {
            char[] s = Integer.toString(i * i).toCharArray();
            PRE_SUM[i] = PRE_SUM[i - 1] + (dfs(s, i, 0, 0) ? i * i : 0);
        }
    }

    public int punishmentNumber(int n) {
        return PRE_SUM[n];
    }

    static boolean dfs(char[] s, int i, int p, int sum) {
        if (p == s.length) {
            return sum == i;
        }

        int x = 0;
        for (int j = p; j < s.length; j++) {
            x = x * 10 + s[j] - '0';
            if (dfs(s, i, j + 1, sum + x)) {
                return true;
            }
        }
        return false;
    }
}

class Solution2 {
    Map<String, Set<Integer>> cache = new HashMap<>();

    public int punishmentNumber(int n) {
        int ans = 0;
        for (int x = 0; x <= n; x++) {
            if (isValid(x)) {
                ans += x * x;
            }
        }
        return ans;
    }

    boolean isValid(int num) {
        int prod = num * num;
        String prodStr = Integer.toString(prod);
        Set<Integer> res = groups(prodStr);
        return res.contains(num);
    }

    Set<Integer> groups(String str) {
        if (cache.containsKey(str)) {
            return cache.get(str);
        }

        Set<Integer> set = new HashSet<>();
        int N = str.length();
        if (N == 1) {
            set.add(Integer.valueOf(str));
            cache.put(str, set);
            return set;
        }

        for (int i = 1; i <= N; i++) {
            String curr = str.substring(0, i);
            int currValue = Integer.valueOf(curr);
            if (i == N) {
                set.add(currValue);
                continue;
            }
            String after = str.substring(i);
            Set<Integer> temp = groups(after);
            for (int x : temp) {
                set.add(currValue + x);
            }
        }
        cache.put(str, set);
        return set;
    }
}

public class Main {
    public static void main(String[] args) {
        test(10, 182);
    }

    private static void test(int n, int expect) {
        int output = new Solution().punishmentNumber(n);
        String desc = String.format("n=%d", n);
        LeetCodeUtils.test(desc, output, expect);
    }
}
