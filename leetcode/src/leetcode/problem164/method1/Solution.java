package leetcode.problem164.method1;

import java.util.*;

class Solution {
    public int maximumGap(int[] nums) {
        int len = nums.length;
        
        if(len < 2){
            return 0;
        }
        
        List<Integer> list = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for(int n : nums){
            list.add(n);
            max = Math.max(max, n);
        }
        
        if(max == 0){
            return 0;
        }
        
        int base = 1;
        while(base <= max){
            base *= 10;
        }
        
        return maxGap(list, base / 10);
    }
    
    int maxGap(List<Integer> nums, int base){
        if(base == 0){
            return 0;
        }
        
        int mai = Integer.MAX_VALUE;
        int max[] = new int[10];
        int min[] = new int[]{mai,mai,mai,mai,mai,mai,mai,mai,mai,mai};
        @SuppressWarnings("unchecked")
		List<Integer>[] buckets = new List[10];
        for(int i = 0; i < 10; ++i){
            buckets[i] = new LinkedList<>();
        }
        
        for(int n : nums){
            int m = n / base;
            buckets[m].add(n % base);
            max[m] = Math.max(max[m], n);
            min[m] = Math.min(min[m], n);
        }
        
        int maxgap = 0;
        int premin = -1;
        for(int i = 9; i >= 0; --i){
            if(buckets[i].size() != 0){
                if(premin != -1){
                    maxgap = Math.max(maxgap, premin - max[i]);
                }
                premin = min[i];
            }
        }
        
        if(maxgap >= base - 1){
            return maxgap;
        }
        
        for(int i = 0; i < 10; ++i){
            maxgap = Math.max(maxgap, maxGap(buckets[i], base / 10));
            if(maxgap >= base - 1){
                return maxgap;
            }
        }
        
        return maxgap;
    }

	public static void main(String[] args) {
		//int[] nums = {3, 6, 9, 1};
		int[] nums = {1,10000000};
		
		
		Solution s = new Solution();
		System.out.println(s.maximumGap(nums));
	}

}
