package leetcode.problem219.method1;

import java.util.*;

public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length ==0){
            return false;
        } 
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; ++i){
            Integer pos = null;
            pos = map.get(nums[i]);
            if(pos != null && i - pos <= k){
                return true;
            } else {
                map.put(nums[i], i);
            }
            
        }
        return false;
    }
}
