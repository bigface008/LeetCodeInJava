package org.leetcode.learn;


import org.leetcode.utils.LeetCodeUtils;

class Base {
    int a;

    Base(int a) {
        this.a = a;
    }
}

class Derived extends Base {
    int a;

    Derived(int a, int b) {
        super(b);
        this.a = a;
    }
}

public class KMP {
    int[] makeLPS(String pattern) {
        final int N = pattern.length();
        int[] lps = new int[N];
        int i = 1;
        int len = 0;
        while (i < N) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    int run(String str1, String str2) {
        int[] table = makeLPS(str2);
        final int N = str1.length();
        final int M = str2.length();
        int j = 0;
        for (int i = 0; i < N; i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = table[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == M) {
                return i - M + 1;
            }
        }
        return -1;
    }

    private static final int flag = 1;

    public static void main(String[] args) {

    }

    private static void check(String str1, String str2, int expect) {
        int output = new KMP().run(str1, str2);
        String desc = String.format("str1=%s, str2=%s", str1, str2);
        LeetCodeUtils.test(desc, output, expect);
    }
}
