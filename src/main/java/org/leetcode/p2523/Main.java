package org.leetcode.p2523;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/closest-prime-numbers-in-range/?envType=daily-question&envId=2025-03-07
//class Solution {
//    public int[] closestPrimes(int left, int right) {
//        boolean[] isPrime = new boolean[right + 1];
//        Arrays.fill(isPrime, true);
//        isPrime[0] = false;
//        isPrime[1] = false;
//        List<Integer> primes = new ArrayList<>();
//        for (int i = 2; i < right + 1; i++) {
//            if (isPrime[i]) {
//                primes.add(i);
//            }
//            for (int p : primes) {
//                if (i * p > right) {
//                    break;
//                }
//                isPrime[i * p] = false;
//                if (i % p == 0) {
//                    break;
//                }
//            }
//        }
//
//        int prevPrime = -1;
//        int minDiff = Integer.MAX_VALUE;
//        int[] ans = {-1, -1};
//        for (int i = left; i < right + 1; i++) {
//            if (isPrime[i]) {
//                if (prevPrime != -1 && minDiff > i - prevPrime) {
//                    minDiff = i - prevPrime;
//                    ans[0] = prevPrime;
//                    ans[1] = i;
//                }
//                prevPrime = i;
//            }
//        }
//        return ans;
//    }
//}

//class Solution {
//    public int[] closestPrimes(int left, int right) {
//        boolean[] isPrime = new boolean[right + 1];
//        Arrays.fill(isPrime, true);
//        isPrime[0] = false;
//        isPrime[1] = false;
//        for (int i = 2; i <= (int) Math.sqrt(right + 1); i++) {
//            if (isPrime[i]) {
//                for (int j = i * i; j < right + 1; j += i) {
//                    isPrime[j] = false;
//                }
//            }
//        }
//        int prevPrime = -1;
//        int minDiff = Integer.MAX_VALUE;
//        int[] ans = {-1, -1};
//        for (int i = left; i < right + 1; i++) {
//            if (isPrime[i]) {
//                if (prevPrime != -1 && minDiff > i - prevPrime) {
//                    minDiff = i - prevPrime;
//                    ans[0] = prevPrime;
//                    ans[1] = i;
//                }
//                prevPrime = i;
//            }
//        }
//        return ans;
//    }
//}

public class Main {
}
