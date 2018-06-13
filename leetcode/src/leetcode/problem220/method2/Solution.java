package leetcode.problem220.method2;

import java.util.*;


// win 95.8%, but O(n*(min(t,k)))
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length ==0){
            return false;
        } 
        
        ++k;
        
        Set<Long> set = new HashSet<Long>();
        
        set.add((long)nums[0]);
        
        for(int i = 1; i < nums.length && i < k; ++i){
            if(t <= k){ // O(t)
                for(long j = (long)nums[i]-(long)t; j <= (long)nums[i]+(long)t; ++j){
                    if(set.contains(j)){
                        return true;
                    }
                }
            } else { // O(k)
                for(Long j : set){
                    if(Math.abs(j - (long)nums[i]) <= t){
                        return true;
                    }
                }
            }
            
            set.add((long)nums[i]);
        }
        
        if(k >= nums.length) {
            return false;
        }
        
        for(int s = 0, e = k; e < nums.length; ++s, ++e){
            set.remove((long)nums[s]);
            
            if(t <= k){ // O(t)
                for(long j = (long)nums[e]-(long)t; j <= (long)nums[e]+(long)t; ++j){
                    if(set.contains(j)){
                        return true;
                    }
                }
            } else { // O(k)
                for(Long j : set){
                    if(Math.abs(j - (long)nums[e]) <= t){
                        return true;
                    }
                }
            }
            
            set.add((long)nums[e]);
        }
        return false;
    }


	public static void main(String[] args) {
		int[] nums = {1,5,9,1,5,9};
		int k = 2;
		int t = 3;
		
		Solution s = new Solution();
		System.out.println(s.containsNearbyAlmostDuplicate(nums, k, t));

	}

}
