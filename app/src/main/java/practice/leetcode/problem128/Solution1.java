package practice.leetcode.problem128;

import java.util.HashSet;
import java.util.Set;

// Time Limit Exceeded at case 79
public class Solution1 {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<>();

        for(int n : nums) {
            numSet.add(n);
        }

        int maxLen = 0; 
        for (int n : nums) {
            if (!numSet.contains(n - 1)) { // the first
                int curNum = n;

                while (numSet.contains(curNum + 1)) {
                    curNum ++;
                }

                maxLen = Math.max(maxLen, curNum - n + 1);
            }
        }

        return maxLen;
    }


}
