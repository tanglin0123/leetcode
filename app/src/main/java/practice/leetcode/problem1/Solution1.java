package practice.leetcode.problem1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; ++i) {
            int n = nums[i];

            int m = target - n;

            Integer idx = map.get(m);

            if(idx != null) {
                return new int[]{idx, i};
            }

            map.put(n, i);
        }

        return new int[0];
    }

    public static void main(String[] args) {
        Solution1 sln = new Solution1();

        int[] nums = new int[]{3,2,4};
        int target = 6;

        int[] result = sln.twoSum(nums, target);

        System.out.println(Arrays.toString(result));
    }
}
