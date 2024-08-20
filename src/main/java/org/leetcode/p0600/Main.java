package org.leetcode.p0600;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones/description/
class Solution {
    public int findIntegers(int n) {
        final int LEN = getBinLen(n);
        if (LEN <= 3) {
            if (n <= 2) {
                return n + 1;
            } else if (n <= 5) {
                return n;
            } else {
                return 5;
            }
        }
        int[] dp = new int[LEN - 1];
        int[] preSum = new int[LEN - 1];
        dp[0] = 2;
        dp[1] = 1;
        dp[2] = 2;
        preSum[0] = 2;
        preSum[1] = 3;
        preSum[2] = 5;
        int ans = 5;
        for (int i = 3; i < LEN - 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            ans += dp[i];
            preSum[i] = ans;
        }
        if (n == (1 << (LEN - 1))) {
            return ans + 1;
        }
        boolean isPrevOne = true;
        for (int l = LEN - 1; l > 0; l--) {
            if (((1 << (l - 1)) & n) != 0) { // 1 at length of l
                if (isPrevOne) {
                    if (l == 1) {
                        ans += 1;
                    } else {
                        ans += preSum[l - 2];
                        break;
                    }
                } else {
                    if (l == 1) {
                        ans += 2; // end
                    } else {
                        ans += preSum[l - 2];
                    }
                }
                isPrevOne = true;
            } else {
                if (l == 1) {
                    ans++;
                }
                isPrevOne = false;
            }
        }
        return ans;
    }

    int getBinLen(int n) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(n);
    }
}

class Solution3 {
    int LEN = 32;
    int[] dp;

    public int findIntegers(int n) {
        LEN = getBinLen(n);
        if (LEN <= 3) {
            if (n <= 2) {
                return n + 1;
            } else if (n <= 5) {
                return n;
            } else {
                return 5;
            }
        }
        dp = new int[LEN - 1];
        dp[0] = 2;
        dp[1] = 1;
        dp[2] = 2;
        int ans = 5;
        for (int i = 3; i < LEN - 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            ans += dp[i];
        }
        ans += recFindInt(n - (int) Math.pow(2, LEN - 1));
        return ans;
    }

    int recFindInt(int n) {
        int currLen = getBinLen(n);
        if (currLen <= 3) {
            if (n <= 2) {
                return n + 1;
            } else if (n <= 5) {
                return n;
            } else {
                return 5;
            }
        }
        return dp[currLen - 2] + recFindInt(n - (int) Math.pow(2, currLen - 1));
    }

    int getBinLen(int n) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(n);
    }
}

class Solution2 {
    public int findIntegers(int n) {
        final int LEN = 32;
        int ans = 0;
        for (int num = 0; num <= n; num++) {
            boolean no2One = true;
            for (int i = 0; i < LEN; i++) {
                int mask1 = 1 << i;
                if ((num & mask1) != 0) {
                    int mask2 = mask1 << 1;
                    if ((num & mask2) != 0) {
                        no2One = false;
                        break;
                    }
                }
            }
            if (no2One) {
                ans++;
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
//        test(5, 5);
//        test(1, 2);
//        test(2, 3);
//        test(12, 8);
//        test(8, 6);
        test(10, 8);
        test(11, 8);
        test(20, 12);
    }

    private static void test(int n, int expect) {
        Solution solution = new Solution();
        int output = solution.findIntegers(n);
        String desc = String.format("find integers n=%d", n);
        LeetCodeUtils.test(desc, output, expect);
    }
}
