package practice.meta.leetcode.p2817;

import java.util.List;

// Time Limit Exceeded 2010 / 2013 testcases passed
public class Solution1 {
    public int minAbsoluteDifference(List<Integer> nums, int x) {

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size() - x; ++i) {
            for (int j = i + x; j < nums.size(); ++j) {
                int diff = Math.abs(nums.get(i) - nums.get(j));
                min = Math.min(min, diff);
            }
        }

        return min;
    }
}
