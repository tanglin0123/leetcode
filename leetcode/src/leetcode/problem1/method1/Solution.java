package leetcode.problem1.method1;

import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); 
    	
    	if(nums == null || nums.length < 2){
    		return null;
    	}
    	
    	for(int i = 0; i < nums.length; ++i){
    		int n0 = nums[i];
    		int n1 = target - n0;
    		Integer i0 = map.get(n1); 
    		if(i0 == null){
    			map.put(n0, i);
    		} else{
    			return new int[]{ i0.intValue(), i};
    		}
    	}
    	
    	return null;
    }
    
    public static void main(String[] args){
    	Solution s = new Solution();
    	
    	int[] nums = new int[]{2,7,11,15};
    	int target = 9;
    	
    	int[] r = s.twoSum(nums, target);
    	System.out.println(r[0] + ", " + r[1]);
    }
}