package org.leetcode.p1813;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/sentence-similarity-iii/description/?envType=daily-question&envId=2024-10-06
class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] arr1 = sentence1.split(" ");
        String[] arr2 = sentence2.split(" ");
        if (arr1.length == arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                if (!arr1[i].equals(arr2[i])) {
                    return false;
                }
            }
            return true;
        }
        if (arr1.length > arr2.length) {
            String[] temp = arr2;
            arr2 = arr1;
            arr1 = temp;
        }

        final int N1 = arr1.length, N2 = arr2.length;
        boolean hasInserted = false;
        int i = 0, j = 0;
        for (; i < N1; i++) {
            if (!arr1[i].equals(arr2[j])) {
                break;
            }
            j++;
        }
        int i1 = i, j1 = j;
        i = N1 - 1;
        j = N2 - 1;
        for (; i >= 0; i--) {
            if (!arr1[i].equals(arr2[j])) {
                break;
            }
            j--;
        }
        int i2 = i, j2 = j;
        return i1 > i2;
//        return j == N2;
    }
}


//class Solution {
//    public boolean areSentencesSimilar(String sentence1, String sentence2) {
//        String[] arr1 = sentence1.split(" ");
//        String[] arr2 = sentence2.split(" ");
//        if (arr1.length == arr2.length) {
//            for (int i = 0; i < arr1.length; i++) {
//                if (!arr1[i].equals(arr2[i])) {
//                    return false;
//                }
//            }
//            return true;
//        }
//        if (arr1.length > arr2.length) {
//            String[] temp = arr2;
//            arr2 = arr1;
//            arr1 = temp;
//        }
//
//        final int N1 = arr1.length, N2 = arr2.length;
//        boolean hasInserted = false;
//        int j = 0;
//        for (int i = 0; i < N1; i++) {
//            String s1 = arr1[i];
//            int jStart = j;
//            while (j < N2 && !arr2[j].equals(s1)) {
//                j++;
//            }
//            if (jStart != j) {
//                if (hasInserted) {
//                    return false;
//                }
//                hasInserted = true;
//            }
//            j++;
//        }
//        if (j != N2 && hasInserted) {
//            return false;
//        }
//        return true;
////        return j == N2;
//    }
//}

public class Main {
    public static void main(String[] args) {
        test("of", "A lot of words", false);
        test("Eating right now", "Eating", true);
        test("A", "a A b A", true);
    }

    static void test(String seq1, String seq2, boolean expect) {
        boolean output = new Solution().areSentencesSimilar(seq1, seq2);
        String desc = String.format("similar seq1=%s seq2=%s", seq1, seq2);
        LeetCodeUtils.test(desc, output, expect);
    }
}
