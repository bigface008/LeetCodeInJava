package org.leetcode.p1942;

import java.util.*;

// https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/?envType=daily-question&envId=2024-10-11
class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        final int N = times.length;
        int targetArrival = times[targetFriend][0];
        Arrays.sort(times, (a, b) -> { return a[0] - b[0]; });
        PriorityQueue<Integer> emptySeats = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            emptySeats.offer(i);
        }
        // leave time, seat idx
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> { return a[0] - b[0]; });
        for (int i = 0; i < N; i++) {
            while (!minHeap.isEmpty() && minHeap.peek()[0] <= times[i][0]) {
                emptySeats.offer(minHeap.poll()[1]);
            }
            if (times[i][0] == targetArrival) {
                break;
            }
            minHeap.offer(new int[]{times[i][1], emptySeats.poll()});
        }
        return emptySeats.peek();
    }
}

//class Solution {
//    public int smallestChair(int[][] times, int targetFriend) {
//        final int N = times.length;
//        int targetArrival = times[targetFriend][0];
//        boolean[] seats = new boolean[N];
//        // time, i
//        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] == o2[0]) {
//                    boolean is1Leave = times[o1[1]][1] == o1[0];
//                    if (is1Leave) {
//                        return -1;
//                    } else {
//                        return 1;
//                    }
//                } else {
//                    return o1[0] - o2[0];
//                }
//            }
//        });
//        for (int i = 0; i < N; i++) {
//            int[] time = times[i];
//            minHeap.offer(new int[]{time[0], i});
//            minHeap.offer(new int[]{time[1], i});
//        }
//        int minEmptyIdx = 0;
//        Map<Integer, Integer> seatMap = new HashMap<>();
//        while (!minHeap.isEmpty() && minHeap.peek()[1] != targetFriend) {
//            int[] info = minHeap.poll();
//            int idx = info[1], time = info[0];
//            boolean isArrival = times[idx][0] == time;
//            if (isArrival) {
//                seatMap.put(idx, minEmptyIdx);
//                seats[minEmptyIdx] = true;
//                for (int i = minEmptyIdx; i < N; i++) {
//                    if (!seats[i]) {
//                        minEmptyIdx = i;
//                        break;
//                    }
//                }
//                // TODO
//            } else {
//                int seatPos = seatMap.get(idx);
//                seats[seatPos] = false;
//                minEmptyIdx = Math.min(minEmptyIdx, seatPos);
//            }
//        }
//        return minEmptyIdx;
//    }
//}

public class Main {
    public static void main(String[] args) {

    }
}
