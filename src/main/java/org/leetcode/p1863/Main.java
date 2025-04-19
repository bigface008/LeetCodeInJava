package org.leetcode.p1863;

// https://leetcode.cn/problems/sum-of-all-subset-xor-totals/?envType=daily-question&envId=2025-04-05
class Solution {
    int N = 0;
    int[] nums;
    int ans = 0;

    public int subsetXORSum(int[] nums) {
        N = nums.length;
        this.nums = nums;
        dfs(0, 0);
        return ans;
    }

    void dfs(int i, int res) {
        if (i == N) {
            ans += res;
            return;
        }
        dfs(i + 1, res ^ nums[i]);
        dfs(i + 1, res);
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
