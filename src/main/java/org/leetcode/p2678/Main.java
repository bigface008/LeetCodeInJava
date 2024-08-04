package org.leetcode.p2678;

// https://leetcode.com/problems/number-of-senior-citizens/?envType=daily-question&envId=2024-08-01
class Solution {
    public int countSeniors(String[] details) {
        int ans = 0;
        for (String info : details) {
            String ageStr = info.substring(11, 13);
            int age = Integer.parseInt(ageStr);
            if (age > 60) {
                ans++;
            }
        }
        return ans;
    }
}

public class Main {
}
