package org.leetcode.p1530;

import org.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    int ans = 0;

    public int countPairs(TreeNode root, int distance) {
        ans = 0;
        dfs(root, distance);
        return ans;
    }

    private List<Integer> dfs(TreeNode root, int distance) {
        if (root == null) {
            return new ArrayList<>();
        }
        if (root.left == null && root.right == null) {
            List<Integer> temp = new ArrayList<>();
            temp.add(0);
            return temp;
        }
        List<Integer> ret = new ArrayList<>();
        List<Integer> leftList = dfs(root.left, distance);
        for (int i = 0; i < leftList.size(); i++) {
            int temp = leftList.get(i) + 1;
            leftList.set(i, temp);
            if (temp > distance) {
                continue;
            }
            ret.add(temp);
        }
        List<Integer> rightList = dfs(root.right, distance);
        for (int i = 0; i < rightList.size(); i++) {
            int temp = rightList.get(i) + 1;
            rightList.set(i, temp);
            if (temp > distance) {
                continue;
            }
            ret.add(temp);
        }
        for (int leftLen : leftList) {
            for (int rightLen : rightList) {
                if (leftLen + rightLen <= distance) {
                    ans++;
                }
            }
        }
        return ret;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
