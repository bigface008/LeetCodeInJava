package org.leetcode.p3211;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.cn/problems/generate-binary-strings-without-adjacent-zeros/?envType=daily-question&envId=2024-10-29
class Solution {
    List<String> ans = new LinkedList<>();
    List<Character> path;
    int N;

    public List<String> validStrings(int n) {
        path = new ArrayList<>(n);
        N = n;
        dfs();
        return ans;
    }

    private void dfs() {
        if (path.size() == N) {
            StringBuilder builder = new StringBuilder();
            for (char ch : path) {
                builder.append(ch);
            }
            ans.add(builder.toString());
            return;
        }
        if (path.isEmpty()) {
            path.add('0');
            dfs();
            path.set(0, '1');
            dfs();
            path.remove(path.size() - 1);
        } else {
            char prevCh = path.get(path.size() - 1);
            if (prevCh == '0') {
                path.add('1');
                dfs();
                path.remove(path.size() - 1);
            } else {
                path.add('0');
                dfs();
                path.set(path.size() - 1, '1');
                dfs();
                path.remove(path.size() - 1);
            }
        }
    }
}

public class Main {
}
