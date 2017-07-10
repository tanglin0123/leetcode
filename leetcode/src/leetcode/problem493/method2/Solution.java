package leetcode.problem493.method2;

import java.util.*;

// exceed time limit

//Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
//You need to return the number of important reverse pairs in the given array.
public class Solution {
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length <=1){
            return 0;
        }
        
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        
        for(int i = 0; i < nums.length; ++i){
            Set<Integer> pos = map.get(nums[i]);
            if(pos == null){
            	pos = new HashSet<Integer>();
            	map.put(nums[i],pos);
            }
            pos.add(i);
            
        }
        
        Object[] ka = map.keySet().toArray();
        Arrays.sort(ka);
        
        int r = 0;
        for(int j = nums.length -1 ; j > 0; --j){
            int k = 0;
            int key = (Integer)ka[k];
            while(k < ka.length){
                key = (Integer)ka[k];
            
                if(key %2 == 0){
                    if(key/2 > nums[j]){ 
                    	break;
                    }
                } else {
                	if((key-1)/2 >= nums[j]){
                        break;
                    }
                }
                
                ++k;
            }
            
            for(; k < ka.length; ++k){
            	Set<Integer> pos = map.get(ka[k]);
            	for(int i : pos){
            		if(i < j){
            			++r;
            		}
            	}
            }
        }
        
        return r;
    }


	public static void main(String[] args) {
		//int[] nums = new int[]{2,4,3,5,1};
		int[] nums = new int[]{1,3,2,3,1};
		
		Solution s = new Solution();
		System.out.println(s.reversePairs(nums));
	}

}
