package org.leetcode.p1092;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/shortest-common-supersequence/?envType=daily-question&envId=2025-02-28
class Solution {
    String str1, str2;
    int N1, N2;
    int[][] dp;

    public String shortestCommonSupersequence(String str1, String str2) {
        N1 = str1.length();
        N2 = str2.length();
        this.str1 = str1;
        this.str2 = str2;
        dp = new int[N1 + 1][N2 + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        dfs(0, 0);
        return dfs2(0, 0);
    }

    private int dfs(int i1, int i2) {
        if (dp[i1][i2] != -1) {
            return dp[i1][i2];
        }
        int res = 0;
        if (i2 == N2) {
            res = N1 - i1;
        } else if (i1 == N1) {
            res = N2 - i2;
        } else {
            char ch1 = str1.charAt(i1);
            char ch2 = str2.charAt(i2);
            if (ch1 == ch2) {
                res = 1 + dfs(i1 + 1, i2 + 1);
            } else {
                res = Math.min(dfs(i1 + 1, i2), dfs(i1, i2 + 1)) + 1;
            }
        }
        dp[i1][i2] = res;
        return res;
    }

    private String dfs2(int i1, int i2) {
//        var m1 = dp2.get(i1);
//        if (m1 != null && m1.containsKey(i2)) {
//            return m1.get(i2);
//        }
        String res = "";
        if (i2 == N2) {
            res = str1.substring(i1, N1);
        } else if (i1 == N1) {
            res = str2.substring(i2, N2);
        } else {
            char ch1 = str1.charAt(i1);
            char ch2 = str2.charAt(i2);
            if (ch1 == ch2) {
                res = ch1 + dfs2(i1 + 1, i2 + 1);
            } else {
                if (dfs(i1 + 1, i2) < dfs(i1, i2 + 1)) {
                    res = ch1 + dfs2(i1 + 1, i2);
                } else {
                    res = ch2 + dfs2(i1, i2 + 1);
                }
            }
        }
//        dp2.computeIfAbsent(i1, k -> new HashMap<>()).put(i2, res);
        return res;
    }
}


class Solution3 {
    int N1 = 0, N2 = 0;
    String str1, str2;
    Map<Integer, Map<Integer, String>> dp = new HashMap<>();

    public String shortestCommonSupersequence(String str1, String str2) {
        N1 = str1.length();
        N2 = str2.length();
        this.str1 = str1;
        this.str2 = str2;
        return dfs(0, 0);
    }

    private String dfs(int i1, int i2) {
        Map<Integer, String> m1 = dp.get(i1);
        if (m1 != null && m1.containsKey(i2)) {
            return m1.get(i2);
        }
        String res = "";
        if (i1 == N1) {
            res = str2.substring(i2, N2);
        } else if (i2 == N2) {
            res = str1.substring(i1, N1);
        } else {
            int minLen = N1 - i1 + N2 - i2;
            char ch1 = str1.charAt(i1);
            char ch2 = str2.charAt(i2);
            for (int i = i2; i < N2; i++) {
                if (str2.charAt(i) == ch1) {
                    int len = i + 1 - i2 + dfs(i1 + 1, i + 1).length();
                    if (len < minLen) {
                        res = str2.substring(i2, i + 1) + dfs(i1 + 1, i + 1);
                        minLen = len;
                    }
                    break;
                } else {
                    int len = i - i2 + 1 + dfs(i1 + 1, i).length();
                    if (len < minLen) {
                        res = str2.substring(i2, i) + Character.toString(ch1) + dfs(i1 + 1, i);
                        minLen = len;
                    }
                }
            }
            for (int i = i1; i < N1; i++) {
                if (str1.charAt(i) == ch2) {
                    int len = i + 1 - i1 + dfs(i + 1, i2 + 1).length();
                    if (len < minLen) {
                        res = str1.substring(i1, i + 1) + dfs(i + 1, i2 + 1);
                        minLen = len;
                    }
                    break;
                } else {
                    int len = i - i1 + 1 + dfs(i, i2 + 1).length();
                    if (len < minLen) {
                        res = str1.substring(i1, i) + Character.toString(ch2) + dfs(i, i2 + 1);
                        minLen = len;
                    }
                }
            }
            if (minLen == N1 - i1 + N2 - i2) {
                res = str1.substring(i1, N1) + str2.substring(i2, N2);
            }
        }
        dp.computeIfAbsent(i1, k -> new HashMap<>()).put(i2, res);
        return res;
    }
}

//class Solution {
//    public String shortestCommonSupersequence(String str1, String str2) {
//        final int N1 = str1.length();
//        final int N2 = str2.length();
//        String[][] dp = new String[N1 + 1][N2 + 1];
//        dp[N1][N2] = "";
//        for (int i = 0; i < N2; i++) {
//            dp[N1][i] = str2.substring(i, N2);
//        }
//        for (int i = 0; i < N1; i++) {
//            dp[i][N2] = str1.substring(i, N1);
//        }
//        for (int i1 = N1 - 1; i1 >= 0; i1--) {
//            for (int i2 = N2 - 1; i2 >= 0; i2--) {
////                String res = str1.substring(i1, N1) + str2.substring(i2, N2);
//                String res = "";
//                int minLen = N1 - i1 + N2 - i2;
//                char ch1 = str1.charAt(i1);
//                char ch2 = str2.charAt(i2);
//                for (int i = i2; i < N2; i++) {
//                    if (str2.charAt(i) == ch1) {
//                        int len = i + 1 - i2 + dp[i1 + 1][i + 1].length();
//                        if (len < minLen) {
//                            res = str2.substring(i2, i + 1) + dp[i1 + 1][i + 1];
//                            minLen = len;
//                        }
//                        break;
//                    } else {
//                        int len = i - i2 + 1 + dp[i1 + 1][i].length();
//                        if (len < minLen) {
//                            res = str2.substring(i2, i) + Character.toString(ch1) + dp[i1 + 1][i];
//                            minLen = len;
//                        }
//                    }
//                }
//                for (int i = i1; i < N1; i++) {
//                    if (str1.charAt(i) == ch2) {
//                        int len = i + 1 - i1 + dp[i + 1][i2 + 1].length();
//                        if (len < minLen) {
//                            res = str1.substring(i1, i + 1) + dp[i + 1][i2 + 1];
//                            minLen = len;
//                        }
//                        break;
//                    } else {
//                        int len = i - i1 + 1 + dp[i][i2 + 1].length();
//                        if (len < minLen) {
//                            res = str1.substring(i1, i) + Character.toString(ch2) + dp[i][i2 + 1];
//                            minLen = len;
//                        }
//                    }
//                }
//                if (minLen == N1 - i1 + N2 - i2) {
//                    res = str1.substring(i1, N1) + str2.substring(i2, N2);
//                }
//                dp[i1][i2] = res;
//            }
//        }
////        System.out.print("  ");
////        for (int j = 0; j <= N2; j++) {
////            String si = Integer.toString(j);
////            if (si.length() < N2 + N1) {
////                si += " ".repeat(N2 + N1 - si.length());
////            }
////            System.out.print(si);
////            System.out.print('\t');
////        }
////        System.out.println();
////        for (int i = 0; i <= N1; i++) {
//////            System.out.printf("%d %s\n", i, Arrays.toString(dp[i]));
////            System.out.print(i);
////            System.out.print(" ");
////            for (int j = 0; j <= N2; j++) {
////                String word = dp[i][j];
////                if (word.length() < N2 + N1) {
////                    word += " ".repeat(N2 + N1 - word.length());
////                }
////                System.out.print(word);
////                System.out.print("\t");
////            }
////            System.out.println();
////        }
//        return dp[0][0];
//    }
//}

public class Main {
    public static void main(String[] args) {
        check("abac", "cab", "cabac");
        check("bbbaaaba", "bbababbb", "bbbaaababbb");
        check("baaacbcbc", "bacbcaca", "baaacbcbaca");
        check("bbcbcaabc", "cacaabaaaa", "bbcbacaabcaaaa");
    }

    private static void check(String str1, String str2, String expect) {
        String actual = new Solution().shortestCommonSupersequence(str1, str2);
        String desc = String.format("str1=%s str2=%s", str1, str2);
        LeetCodeUtils.test(desc, actual, expect);
    }
}
