package org.leetcode.p2491;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/divide-players-into-teams-of-equal-skill
class Solution {
    public long dividePlayers(int[] skill) {
        int sum = Arrays.stream(skill).sum();
        final int N = skill.length;
        if ((N % 2 != 0) || (sum % (N / 2) != 0)) {
            return -1;
        }
        int target = sum / (N / 2);
        Arrays.sort(skill);
        int left = 0, right = skill.length - 1;
        long ans = 0;
        while (left < right) {
            if (skill[left] + skill[right] != target) {
                return -1;
            }
            ans += (long) skill[left] * skill[right];
            left++;
            right--;
        }
        return ans;
    }
}


//class Solution {
//    public long dividePlayers(int[] skill) {
//        int sum = Arrays.stream(skill).sum();
//        final int N = skill.length;
//        if ((N % 2 != 0) || (sum % (N / 2) != 0)) {
//            return -1;
//        }
//        int pairSum = sum / (N / 2);
//        long ans = 0;
//        Map<Integer, Integer> counter = new HashMap<>();
//        for (int i = 0; i < N; i++) {
//            int x = skill[i];
//            int remain = pairSum - x;
//            if (counter.containsKey(remain)) {
//                int cnt = counter.get(remain) - 1;
//                if (cnt == 0) {
//                    counter.remove(remain);
//                } else {
//                    counter.put(remain, cnt);
//                }
//                ans += (long) remain * x;
//            } else {
//                counter.put(x, counter.getOrDefault(x, 0) + 1);
//            }
//        }
//        if (!counter.isEmpty()) {
//            return -1;
//        }
//        return ans;
//    }
//}

public class Main {
    public static void main(String[] args) {

    }
}
