package org.leetcode.p1456;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
class Solution {
    public int maxVowels(String s, int k) {
        int ans = 0;
        int i = 0;
        for (; i < Math.min(k, s.length()); i++) {
            if (isVowel(s.charAt(i))) {
                ans++;
            }
        }
        int windowVowelCnt = ans;
        for (; i < s.length(); i++) {
            char dropCh = s.charAt(i - k);
            boolean isDropVowel = isVowel(dropCh);
            char currCh = s.charAt(i);
            boolean isCurrVowel = isVowel(currCh);
            if (isCurrVowel) {
                if (!isDropVowel) {
                    windowVowelCnt++;
                    ans = Math.max(ans, windowVowelCnt);
                }
            } else {
                if (isDropVowel) {
                    windowVowelCnt--;
                }
            }
        }
        return ans;
    }
    
    boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' ||ch == 'i' ||ch == 'o' ||ch == 'u';
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
