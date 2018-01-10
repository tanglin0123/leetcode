package leetcode.problem421.method1;

import java.util.*;

// Time limit exceeded at case 29
class Solution {
    public int findMaximumXOR(int[] nums) {
        if(nums == null || nums.length <= 1){
            return 0;
        }
        
        if(nums.length == 2){
            return nums[0] ^ nums[1];
        }
        
        Set<Integer> g0 = new HashSet<>();
        for(int n : nums){
            g0.add(n);
        }
        
        return find(g0, new HashSet<Integer>(), 31);
    }
    
    int find(Set<Integer> g0, Set<Integer> g1, int i){

        int mask = 1 << i;
        
        if(g0.size() + g1.size()<=1){ // one size is 0, the other size is less than 1
            return 0;
        }
        
        if(g0.size()==2 && g1.size()==0){
            Object[] r = g0.toArray();
            return (Integer)r[0]^(Integer)r[1];
        }
        
        if(g0.size()==0 && g1.size()==2){
            Object[] r = g1.toArray();
            return (Integer)r[0]^(Integer)r[1];
        }
        
        if(g0.size()==1 && g1.size()==1){
            Object[] ra = g0.toArray();
            Object[] rb = g1.toArray();
            return (Integer)ra[0]^(Integer)rb[0];
        }
        
        if(g0.isEmpty() || g1.isEmpty()) {
        	Set<Integer> g = g0.isEmpty() ? g1 : g0;
        	for(; i >= 0; --i) {
        		mask = 1 << i;
        		
        		Set<Integer> gg0 = new HashSet<Integer>();
        		Set<Integer> gg1 = new HashSet<Integer>();
        		for(int n : g) {
            		int bit = n & mask;
            		if(bit == 0) {
            			gg0.add(n);
            		}else {
            			gg1.add(n);
            		}
            	}
        		
        		if(!gg0.isEmpty() && !gg1.isEmpty()) {
        			return find(gg0, gg1, i-1);
        		}
        	}
        	
        }

        Set<Integer> g00 = new HashSet<>();
        Set<Integer> g01 = new HashSet<>();
        Set<Integer> g10 = new HashSet<>();
        Set<Integer> g11 = new HashSet<>();
        
        for(int n : g0){
            int bit = n & mask;
            if(bit == 0){
                g00.add(n);
            } else {
                g01.add(n);
            }
        }
        
        for(int n : g1){
            int bit = n & mask;
            if(bit == 0){
                g10.add(n);
            } else {
                g11.add(n);
            }
        }
        
        if( (g00.isEmpty() && g10.isEmpty() ) || (g01.isEmpty() && g11.isEmpty())) {
        	return find(g0, g1, i-1);
        }
        
        int r1 = find(g00, g11, i-1);
        int r2 = find(g10, g01, i-1);
        
        return Math.max(r1, r2);
    }

	public static void main(String[] args) {
//		int[] a = {3,10,5,25,2,8};
		int[] a = {8,10,2};
		
		Solution s = new Solution();
		System.out.println(s.findMaximumXOR(a));
	}

}
