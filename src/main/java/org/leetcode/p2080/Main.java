package org.leetcode.p2080;

import java.util.*;

// https://leetcode.cn/problems/range-frequency-queries/?envType=daily-question&envId=2025-02-18
class RangeFreqQuery {

    Map<Integer, List<Integer>> posMap = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];
            posMap.computeIfAbsent(x, k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> pos = posMap.get(value);
        if (pos == null) {
            return 0;
        }
        int res1 = Collections.binarySearch(pos, left);
        if (res1 < 0) {
            res1 = -(res1 + 1);
        }
        int res2 = Collections.binarySearch(pos, right);
        boolean found = true;
        if (res2 < 0) {
            res2 = -(res2 + 1);
            found = false;
        }
        return res2 - res1 + (found ? 1 : 0);
    }
}

//class RangeFreqQuery {
//
//    List<Map<Integer, Integer>> preSum;
//
//    public RangeFreqQuery(int[] arr) {
//        int N = arr.length;
//        preSum = new ArrayList<>(N + 1);
//        preSum.add(new HashMap<>());
//        for (int i = 0; i < N; i++) {
//            int x = arr[i];
//            Map<Integer, Integer> prevMap = preSum.get(i);
//            Map<Integer, Integer> nextMap = new HashMap<>(prevMap);
//            nextMap.put(x, prevMap.getOrDefault(x, 0) + 1);
//            preSum.add(nextMap);
//        }
//    }
//
//    public int query(int left, int right, int value) {
//        int start = preSum.get(left).getOrDefault(value, 0);
//        int end = preSum.get(right + 1).getOrDefault(value, 0);
//        return end - start;
//    }
//}


//class RangeFreqQuery {
//
//    Map<Integer, int[]> preSumMap = new HashMap<>();
//
//    public RangeFreqQuery(int[] arr) {
//        int N = arr.length;
//        for (int i = 0; i < N; i++) {
//            int x = arr[i];
//            if (preSumMap.containsKey(x)) {
//                int[] temp = preSumMap.get(x);
//                temp[i + 1] = temp[i] + 1;
//            } else {
//                int[] temp = new int[N + 1];
//                temp[i + 1] = 1;
//                preSumMap.put(x, temp);
//            }
//            for (int key : preSumMap.keySet()) {
//                if (key == x) {
//                    continue;
//                }
//                int[] temp = preSumMap.get(key);
//                temp[i + 1] = temp[i];
//            }
//        }
//    }
//
//    public int query(int left, int right, int value) {
//        if (!preSumMap.containsKey(value)) {
//            return 0;
//        }
//        int[] temp = preSumMap.get(value);
//        return temp[right + 1] - temp[left];
//    }
//}

public class Main {
    public static void main(String[] args) {
        {
            RangeFreqQuery query = new RangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
            System.out.println(query.query(1, 2, 4));
            System.out.println(query.query(0, 11, 33));
        }
    }

    private static void test() {

    }
}
