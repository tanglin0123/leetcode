package practice.meta.leetcode.p2817;

import java.util.*;

// using TreeSet https://www.geeksforgeeks.org/java/treeset-in-java-with-examples/
public class Solution2 {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeSet<Integer> set = new TreeSet<>();

        int min = Integer.MAX_VALUE;

        for (int i = x; i < nums.size(); ++i) {
            int n = nums.get(i - x);
            set.add(n);

            int m = nums.get(i);
            Integer ceiling = set.ceiling(m);
            Integer floor = set.floor(m);

            if (ceiling != null) {
                min = Math.min(min, Math.abs(m - ceiling));
            }

            if (floor != null) {
                min = Math.min(min, Math.abs(m - floor));
            }
        }

        return min;
    }
}
