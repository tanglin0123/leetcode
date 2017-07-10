package leetcode.problem523.method2;

import java.util.*;

public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length <= 1){
            return false;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        
        int sum = 0; 
        
        for(int i = 0; i < nums.length; ++ i) {
        		sum += nums[i];
        		if(k != 0) {
        			sum %= k;
        		}
        		Integer pre = map.get(sum);
        		if(pre == null) {
        			map.put(sum, i);
        		} else {
        			if(i - pre >= 2) {
        				return true;
        			}
        		}
        }
        
        return false;
        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
