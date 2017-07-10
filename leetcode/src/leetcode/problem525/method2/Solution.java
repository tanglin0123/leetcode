package leetcode.problem525.method2;

import java.util.*;

public class Solution {
    public int findMaxLength(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        
        for(int i = 0; i < nums.length; ++i) {
        		if(nums[i] == 0) {
        			nums[i] = -1;
        		}
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int sum = 0;
        int max = 0;
        for(int i = 0; i < nums.length; ++i) {
        		sum += nums[i];
        		Integer pre = map.get(sum);
        		if(pre == null) {
        			map.put(sum, i);
        		} else {
        			int len = i - pre;
        			if(max < len) {
        				max = len;
        			}
        		}
        }
        
        return max;
    }

	public static void main(String[] args) {
		int[] a = {0, 1};
		
		Solution s = new Solution();
		System.out.println(s.findMaxLength(a));
	}

}
