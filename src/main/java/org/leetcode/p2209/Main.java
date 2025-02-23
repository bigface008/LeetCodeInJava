package org.leetcode.p2209;

import org.leetcode.utils.LeetCodeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/minimum-white-tiles-after-covering-with-carpets/?envType=daily-question&envId=2025-02-21
//class Solution {
//    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
//        final int N = floor.length();
//        int ans = 0;
//        int windowOneCnt = 0;
//        int[] windows = new int[N - carpetLen + 1];
//        for (int i = 0; i < carpetLen; i++) {
//            if (floor.charAt(i) == '1') {
//                windowOneCnt++;
//            }
//        }
//        windows[0] = windowOneCnt;
//        for (int i = carpetLen; i < N; i++) {
//            if (floor.charAt(i) == '1') {
//                windowOneCnt++;
//                ans++;
//            }
//            if (floor.charAt(i - carpetLen) == '1') {
//                windowOneCnt--;
//            }
//            windows[i] = windowOneCnt;
//        }
//        while (numCarpets > 0) {
//
//            numCarpets--;
//        }
//        return ans;
//    }
//}

class Solution {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        final int N = floor.length();
        int[] floorInfo = new int[N];
        for (int i = 0; i < N; i++) {
            floorInfo[i] = floor.charAt(i) - '0';
        }
        int[][] dp = new int[numCarpets + 1][N];
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                dp[0][i] = floorInfo[0];
            } else {
                dp[0][i] = dp[0][i - 1] + floorInfo[i];
            }
        }
        for (int i = 1; i < numCarpets + 1; i++) {
            for (int j = carpetLen * i; j < N; j++) {
                dp[i][j] = Math.min(dp[i][j - 1] + floorInfo[j], dp[i - 1][j - carpetLen]);
            }
        }
        return dp[numCarpets][N - 1];
    }
}

class Solution3 {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        final int N = floor.length();
        int ans = 0;
        int[] floorInfo = new int[N];
        int allOneCnt = 0;
        for (char ch : floor.toCharArray()) {
            if (ch == '1') {
                allOneCnt++;
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[0] - a[0];
        }); // one_cnt, i, valid
        Map<Integer, int[]> mp = new HashMap<>();
        int windowOneCnt = 0;
        for (int i = 0; i < carpetLen; i++) {
            if (floor.charAt(i) == '1') {
                windowOneCnt++;
                ans++;
            }
            floorInfo[i] = floor.charAt(i) - '0';
        }
        pq.add(new int[]{windowOneCnt, 0, 1});
        mp.put(0, pq.peek());
        for (int i = carpetLen; i < N; i++) {
            if (floor.charAt(i) == '1') {
                windowOneCnt++;
                ans++;
            }
            if (floor.charAt(i - carpetLen) == '1') {
                windowOneCnt--;
            }
            int idx = i - carpetLen + 1;
            int[] info = new int[]{windowOneCnt, idx, 1};
            pq.add(info);
            mp.put(idx, info);
            floorInfo[i] = floor.charAt(i) - '0';
        }
//        System.out.println(allOneCnt == ans);
        while (numCarpets > 0 && !pq.isEmpty()) {
            int[] info = pq.poll();
            int oneCnt = info[0], idx = info[1], valid = info[2];
            if (valid == 0 || oneCnt == 0) {
                continue;
            }
            ans -= oneCnt;
            for (int i = 0; i < carpetLen; i++) {
                floorInfo[idx + i] = 0;
            }
            int newOneCnt = 0;
            // 70 71 72 73 74
            for (int i = idx + carpetLen; i < N && (i - carpetLen + 1) < idx + carpetLen; i++) {
//                System.out.printf("cover after idx=%d i=%d\n", idx, i - carpetLen + 1);
                if (floorInfo[i] == 1) {
                    newOneCnt++;
                }
                if (floorInfo[i - carpetLen] == 1) {
                    newOneCnt--;
                }
                int newIdx = i - carpetLen + 1;
                int[] oldInfo = mp.get(newIdx);
                if (oldInfo != null) {
                    oldInfo[2] = 0;
                }
                if (newOneCnt == 0) {
                    continue;
                }
                int[] newInfo = new int[]{newOneCnt, newIdx, 1};
                mp.put(newIdx, newInfo);
                pq.offer(newInfo);
            }
            newOneCnt = 0;
            for (int i = idx - 1; i >= 0 && (i + carpetLen) > idx; i--) {
//                System.out.printf("cover before idx=%d i=%d\n", idx, i);
                if (floorInfo[i] == 1) {
                    newOneCnt++;
                }
                if (floorInfo[i + carpetLen] == 1) {
                    newOneCnt--;
                }
                int[] oldInfo = mp.get(i);
                if (oldInfo != null) {
                    oldInfo[2] = 0;
                }
                if (newOneCnt == 0) {
                    continue;
                }
                int[] newInfo = new int[]{newOneCnt, i, 1};
                mp.put(i, newInfo);
                pq.offer(newInfo);
            }
            numCarpets--;
        }
        return ans;
    }
}

class Solution2 {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        final int N = floor.length();
        int i = 0;
        int ans = 0;
        while (i < N) {
            if (floor.charAt(i) == '0') {
                i++;
                continue;
            }
            int j = i;
            while (i < N && floor.charAt(i) == '1') {
                i++;
            }
            ans += i - j;
            pq.add(i - j);
        }
        while (numCarpets > 0 && !pq.isEmpty()) {
            int len = pq.poll();
            if (len > carpetLen) {
                pq.offer(len - carpetLen);
                ans -= carpetLen;
            } else {
                ans -= len;
            }
            numCarpets--;
        }
        return ans;
    }

    // dp[f][nc]

    //  = dp[f][nc - 1] - (max(dist_between_black_blocks) - carpetLen)
}

public class Main {
    public static void main(String[] args) {
//        test("10110101", 2, 2, 2);
//        test("11111", 2, 3, 0);
//        test("11010110", 12, 4, 0);
//        test("0010000101101001111010010101110001100111000010100011111011100101011000111101001000010011010", 22, 19, 0);
        test("111111111101001101111110001011111111111111111111011111111111111111111101101011111111111111101100010010111111100111110010110111110111111111100011111011111011111111111110100110101011111111110101001111101111110111111111111101", 43, 5, 0);
    }

    private static void test(String floor, int numCarpets, int carpetLen, int expect) {
        try {
            int output = new Solution().minimumWhiteTiles(floor, numCarpets, carpetLen);
            String desc = String.format("floor=%s numCarpets=%d carpetLen=%d", floor, numCarpets, carpetLen);
            LeetCodeUtils.test(desc, output, expect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
