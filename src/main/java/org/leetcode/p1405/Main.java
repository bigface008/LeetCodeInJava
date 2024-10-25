package org.leetcode.p1405;

import java.io.IOException;
import java.util.Arrays;

// https://leetcode.com/problems/longest-happy-string/?envType=daily-question&envId=2024-10-16
//class Solution {
//    public String longestDiverseString(int a, int b, int c) {
//        int[] arr = {a, b, c};
//        Arrays.sort(arr);
//        a = arr[0];
//        b = arr[1];
//        c = arr[2];
//        if (2 * (a + b + 1) <= c) {
//            return
//        }
//    }
//}

// a 1 b 1 c 7
// ccaccbcc

class TraderConf {

}

interface Gardener {
    String getName();

    void initTrader(TraderConf conf) throws Exception;
}

class CommGardener implements Gardener {
    @Override
    public String getName() {
        return "common";
    }

    @Override
    public void initTrader(TraderConf conf) throws IOException {
        System.out.println("common gardener");
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
