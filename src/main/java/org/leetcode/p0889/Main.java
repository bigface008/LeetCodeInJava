package org.leetcode.p0889;

import org.leetcode.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/?envType=daily-question&envId=2025-02-23
class Solution {
    int[] preorder;
    int[] postorder;
    Map<Integer, Integer> val2Idx = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.preorder = preorder;
        this.postorder = postorder;
        for (int i = 0; i < postorder.length; i++) {
            val2Idx.put(postorder[i], i);
        }
        return dfs(0, preorder.length - 1, 0, postorder.length - 1);
    }

    TreeNode dfs(int preStart, int preEnd, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        int val = preorder[preStart];
        TreeNode node = new TreeNode(val);
        if (preStart == preEnd || postStart == postEnd) {
            return node;
        }
        int leftSubTreeVal = preorder[preStart + 1];
        int leftSubTreeSize = val2Idx.get(leftSubTreeVal) - postStart + 1;
        node.left = dfs(preStart + 1, preStart + leftSubTreeSize, postStart, postStart + leftSubTreeSize - 1);
        node.right = dfs(preStart + leftSubTreeSize + 1, preEnd, postStart + leftSubTreeSize, postEnd);
        return node;
    }
}

class Solution2 {
    int[] preorder;
    int[] postorder;
    Map<Integer, Integer> val2Idx = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.preorder = preorder;
        this.postorder = postorder;
        for (int i = 0; i < preorder.length; i++) {
            val2Idx.put(preorder[i], i);
        }
        return dfs(0, preorder.length - 1, 0, postorder.length - 1);
    }

    TreeNode dfs(int preStart, int preEnd, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        int val = preorder[preStart];
        TreeNode node = new TreeNode(val);
        if (preStart == preEnd || postStart == postEnd) {
            return node;
        }
        int i = 0;
        int set1 = 0, set2 = 0;
        while (i + preStart + 1 <= preEnd) {
            set1 |= (1 << val2Idx.get(preorder[i + preStart + 1]));
            set2 |= (1 << val2Idx.get(postorder[i + postStart]));
            if (set1 == set2) {
                break;
            }
            i++;
        }
        node.left = dfs(preStart + 1, preStart + i + 1, postStart, postStart + i);
        node.right = dfs(preStart + i + 2, preEnd, postStart + i + 1, postEnd);
        return node;
    }
}

public class Main {
}
