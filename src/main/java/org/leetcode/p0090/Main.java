package org.leetcode.p0090;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.cn/problems/subsets-ii/
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private int[] nums;
    private int N = 0;
    private List<Integer> group = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        N = nums.length;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == N) {
            ans.add(new ArrayList<>(group));
            return;
        }
        int x = nums[i];
        group.add(x);
        dfs(i + 1);
        group.remove(group.size() - 1);
        int j = i + 1;
        while (j < N && nums[j] == x) {
            j++;
        }
        dfs(j);
    }
}

public class Main {
}
