package leetcode.problem384.method1;

import java.util.Arrays;
import java.util.Random;

public class Solution {

	int[] nums = null;
	Random rand = new Random();
	
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
    	if(nums == null || nums.length <= 1){
    		return nums;
    	}
    
    	
    	int n = nums.length;
        
    	int[] newnums = Arrays.copyOfRange(nums, 0, n);
        
        for(int i = n-1; i > 0; --i){
        	int idx = rand.nextInt(i+1);
        	if(idx != i){
        		int temp = newnums[i];
        		newnums[i] = newnums[idx];
        		newnums[idx] = temp;
        	}
        }
        
        return newnums;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
