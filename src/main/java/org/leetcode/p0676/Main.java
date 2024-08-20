package org.leetcode.p0676;

import java.util.*;

// https://leetcode.cn/problems/implement-magic-dictionary/description/?envType=daily-question&envId=2024-08-12
class MagicDictionary {
    String[] dictionary;

    public MagicDictionary() {
    }

    public void buildDict(String[] dictionary) {
        this.dictionary = dictionary;
    }

    public boolean search(String searchWord) {
        for (String ds : dictionary) {
            if (ds.length() != searchWord.length()) {
                continue;
            }
            int diffCnt = 0;
            for (int i = 0; i < ds.length() && diffCnt <= 1; i++) {
                if (ds.charAt(i) != searchWord.charAt(i)) {
                    diffCnt++;
                }
            }
            if (diffCnt == 1) {
                return true;
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
