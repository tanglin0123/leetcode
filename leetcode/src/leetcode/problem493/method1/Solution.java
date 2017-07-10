package leetcode.problem493.method1;

import java.util.*;

// understand wrong question
// the solution are actually for question:
//Given an array nums, we call (i, j) an important reverse pair if nums[i] < num[j] and nums[j] < 2*nums[i].
//You need to return the number of important reverse pairs in the given array.
public class Solution {
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length <=1){
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; ++i){
            Integer count = map.get(nums[i]);
            if(count == null){
                map.put(nums[i],1);
            }else{
                map.put(nums[i], count+1);
            }
        }
        
        Object[] ka = map.keySet().toArray();
        Arrays.sort(ka);
        
        int r = 0;
        for(int i = 0; i < ka.length-1; ++i){
            int k = (Integer)ka[i];
            int kcount = map.get(k);
            
            int k2 = 2*k;

            for(int j = i+1; j < ka.length && ((Integer)ka[j])<k2;++j){
                int mcount = map.get((Integer)ka[j]);
                r+=kcount*mcount;
            }
        }
        
        return r;
    }


	public static void main(String[] args) {
		int[] nums = new int[]{2,4,3,5,1};
		
		Solution s = new Solution();
		System.out.println(s.reversePairs(nums));
	}

}
