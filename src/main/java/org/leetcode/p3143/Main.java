package org.leetcode.p3143;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

class Solution {
    class Pair {
        int dist;
        char tag;

        Pair(int x, int y, char tag) {
            this.dist = Math.max(Math.abs(x), Math.abs(y));
            this.tag = tag;
        }
    }

    public int maxPointsInsideSquare(int[][] points, String s) {
        List<Pair> pairs = new ArrayList<>();
        Set<Character> chs = new HashSet<>();
        for (int i = 0; i < points.length; i++) {
            Pair pair = new Pair(points[i][0], points[i][1], s.charAt(i));
            pairs.add(pair);
        }
        pairs.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.dist - o2.dist;
            }
        });
        int i = 0;
        int ans = 0;
        while (i < pairs.size()) {
            var pair = pairs.get(i);
            int currDist = pair.dist;
            int count = 0;
            while (i < pairs.size() && pairs.get(i).dist == currDist) {
                if (chs.contains(pairs.get(i).tag)) {
                    return ans;
                } else {
                    chs.add(pairs.get(i).tag);
                    count++;
                }
                i++;
            }
            ans += count;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test("[[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]]", "abdca", 2);
//        test("[[1,1],[-2,-2],[-2,2]]", "abb", 1);
//        test("[[1,1],[-1,-1],[2,-2]]", "ccd", 0);
//        test("[[1,-1]]", "a", 1);
    }

    private static void test(String pointsStr, String s, int expect) {
        Solution solution = new Solution();
        int output = solution.maxPointsInsideSquare(LeetCodeUtils.make2DIntArray(pointsStr), s);
        String desc = String.format("max points inside square points=%s, s=%s", pointsStr, s);
        LeetCodeUtils.test(desc, output, expect);
    }
}
