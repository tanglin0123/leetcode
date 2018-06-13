package leetcode.problem220.method1;

import java.util.*;

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums==null || nums.length ==0){
            return false;
        }
        
        HashMap<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        for(int i = 0; i < nums.length; ++i){
            Set<Integer> pos = null;
            pos = map.get(nums[i]);
            if(pos == null ){
                pos = new HashSet<Integer>();
                map.put(nums[i], pos);
            }
            pos.add(i);
        }
        
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length; ++i){
            Set<Integer> pos1= map.get(nums[i]);
            for(int j = i; j <= nums.length -1 && nums[j] <= nums[i]+t ; ++j){
                Set<Integer> pos2 = map.get(nums[j]);
                for(int p1: pos1){
                    for(int p2: pos2){
                        int d = Math.abs(p2-p1);
                        if( d>0 && d<= k){
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}