package org.leetcode.p1331;

import java.util.*;

// https://leetcode.com/problems/rank-transform-of-an-array/description/?envType=daily-question&envId=2024-10-02
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        final int N = arr.length;
        int[] dup = arr.clone();
        Arrays.sort(dup);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.putIfAbsent(dup[i], map.size() + 1);
        }
        for (int i = 0; i < N; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }
}

//class Solution {
//    public int[] arrayRankTransform(int[] arr) {
//        final int N = arr.length;
//        if (N == 0) {
//            return new int[]{};
//        }
//        List<int[]> list = new ArrayList<>(N);
//        for (int i = 0; i < N; i++) {
//            list.add(new int[]{arr[i], i});
//        }
//        list.sort((a, b) -> {
//            return a[0] - b[0];
//        });
//        int[] ans = new int[N];
//        int rank = 1;
//        ans[list.get(0)[1]] = 1;
//        for (int i = 1; i < N; i++) {
//            if (list.get(i)[0] != list.get(i - 1)[0]) {
//                rank++;
//            }
//            ans[list.get(i)[1]] = rank;
//        }
//        return ans;
//    }
//}

public class Main {
    public static void main(String[] args) {

    }
}
