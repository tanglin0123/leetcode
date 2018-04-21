package leetcode.problem560.method1;

import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int n = nums.length;

        Map<Long, List<Integer>> sumMap = new HashMap<>();
        long[] sum = new long[n];
        
        sum[0] = nums[0];
        List<Integer> l = new ArrayList<>();
        l.add(0);
        sumMap.put(sum[0], l);
        
        for(int i = 1; i < n; ++i){
            sum[i] = sum[i-1] + nums[i];
            l = sumMap.get(sum[i]);
            if(l == null){
                l = new ArrayList<>();
                sumMap.put(sum[i], l);
            }
            l.add(i);
        }
        
        int count = 0;
        l = sumMap.get((long)k); // important to type cast
        if(l != null) 
            count = l.size();
        
        for(int i = 0; i < n; ++i){
            l = sumMap.get(sum[i] - k);
            if(l == null) 
                continue;
            for(int j : l) {
                if(j < i){
                    ++count;
                } else {
                    break;
                }
            }
        }
        
        return count;
        
    }
}
