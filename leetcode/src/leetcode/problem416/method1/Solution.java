package leetcode.problem416.method1;

import java.util.*;

// backtrace, time limit exceed
public class Solution {
    public boolean canPartition(int[] nums) {
        
    	Arrays.sort(nums);
        
    	int sum = 0;
    	for(int n : nums){
    		sum += n;
    	}
    	
    	int len = nums.length;
    	
    	if(sum % 2 != 0 || len == 1){
    		return false;
    	}
        
    	int target = sum/2;
    	
    	int s = 0;
    	return pick(nums, len-1, s, target);

    }
    
    boolean pick(int[] nums, int end, int sum, int target){
    	
    	if(sum == target){
    		return true;
    	}
    	
    	if(sum > target){
    		return false;
    	}
    	
    	for(int i = end; i >= 0; --i){
    		sum += nums[i];
    		boolean succ = pick(nums, i-1, sum, target);
    		if(succ){
    			return true;
    		}
    		sum -= nums[i];
    	}
    	
    	return false;
    }
    

	public static void main(String[] args) {
		int[] a = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,100};

		
		Solution s = new Solution();
		System.out.println(s.canPartition(a));

	}
}
