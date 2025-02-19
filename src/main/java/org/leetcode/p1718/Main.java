package org.leetcode.p1718;

// 1
// 2 1 2
// 3 1 2 3 2


// 4 ->
// 4 2 1 2 4
// 4 2 3 2 4 3 1

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/?envType=daily-question&envId=2025-02-16
class Solution {
    private Set<Integer> visited = new HashSet<>();

    private int[] res;

    private int n;

    public int[] constructDistancedSequence(int n) {
        res = new int[2 * n - 1];
        this.n = n;
        backtrack(0);
        return res;
    }

    private boolean backtrack(int i) {
        if (i >= 2 * n - 1) {
            return true;
        }
        if (res[i] != 0) {
            return backtrack(i + 1);
        }
        for (int x = n; x >= 1; x--) {
            if (visited.contains(x)) {
                continue;
            }
            if (x == 1) {
                res[i] = 1;
                visited.add(1);
                if (backtrack(i + 1)) {
                    return true;
                }
                res[i] = 0;
                visited.remove(1);
            } else {
                if (i + x >= 2 * n - 1) {
                    continue;
                }
                if (res[i] != 0 || res[i + x] != 0) {
                    continue;
                }
                res[i] = x;
                res[i + x] = x;
                visited.add(x);
                if (backtrack(i + 1)) {
                    return true;
                }
                res[i] = 0;
                res[i + x] = 0;
                visited.remove(x);
            }
        }
        return false;
    }
}

public class Main {
}
