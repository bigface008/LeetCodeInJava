package org.leetcode.p1845;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/seat-reservation-manager/?envType=daily-question&envId=2024-09-30
class SeatManager {
    PriorityQueue<Integer> avaliable = new PriorityQueue<>();
    int seatCnt = 0;

    public SeatManager(int n) {}

    public int reserve() {
        if (!avaliable.isEmpty()) {
            return avaliable.poll();
        }
        seatCnt++;
        return seatCnt;
    }

    public void unreserve(int seatNumber) {
        avaliable.add(seatNumber);
    }
}

//class SeatManager {
//    PriorityQueue<Integer> pq = new PriorityQueue<>();
//
//    public SeatManager(int n) {
//        for (int i = 0; i < n; i++) {
//            pq.add(i + 1);
//        }
//    }
//
//    public int reserve() {
//        if (pq.isEmpty()) {
//            return -1;
//        }
//        return pq.poll();
//    }
//
//    public void unreserve(int seatNumber) {
//        pq.add(seatNumber);
//    }
//}

//class SeatManager {
//
////    List<Boolean> states;
//    private boolean[] states;
//    private int firstEmptySeatIdx = -1;
//
//    public SeatManager(int n) {
//        states = new boolean[n];
//        Arrays.fill(states, false);
//        firstEmptySeatIdx = 0;
//    }
//
//    public int reserve() {
//        if (firstEmptySeatIdx == states.length) {
//            return 0;
//        }
//        int ans = firstEmptySeatIdx + 1;
//        states[firstEmptySeatIdx] = true;
//        for (int i = firstEmptySeatIdx; i < states.length; i++) {
//            if (!states[i]) {
//                break;
//            }
//            firstEmptySeatIdx++;
//        }
//        return ans;
//    }
//
//    public void unreserve(int seatNumber) {
//        states[seatNumber - 1] = false;
//        firstEmptySeatIdx = Math.min(firstEmptySeatIdx, seatNumber - 1);
//    }
//}

public class Main {
    public static void main(String[] args) {
        {
            var manager = new SeatManager(2);
            manager.reserve();
            manager.unreserve(1);
            manager.reserve();
            manager.reserve();
            manager.unreserve(2);
            manager.reserve();
            manager.unreserve(1);
            manager.reserve();
            manager.unreserve(2);
            int ans = manager.reserve();
            System.out.println(ans == 2);
        }
    }
}
