package leetcode.problem421.method2;

import java.util.*;


// top 95.26%
class Solution {
    public int findMaximumXOR(int[] nums) {
        if(nums == null || nums.length <= 1){
            return 0;
        }
        
        if(nums.length == 2){
            return nums[0] ^ nums[1];
        }
        
        Set<Integer> g0 = new HashSet<>(nums.length);
        for(int n : nums){
            g0.add(n);
        }
        
        return find(g0, new HashSet<Integer>(0), 31);
    }
    
    int find(Set<Integer> g0, Set<Integer> g1, int i){

        int mask = 1 << i;
        
        if(g0.size() + g1.size() <= 1){ // one size is 0, the other size is less than 1
            return 0;
        }
        
        if(g0.size() + g1.size() == 2){
        	g0.addAll(g1);
        	Object[] g = g0.toArray();
        	return (Integer)g[0] ^ (Integer)g[1];
        }
        
        if(g0.isEmpty() || g1.isEmpty()) {
        	Set<Integer> g = g0.isEmpty() ? g1 : g0;
        	for(; i >= 0; --i) {
        		mask = 1 << i;
        		
        		Set<Integer> gg0 = new HashSet<Integer>(g.size());
        		Set<Integer> gg1 = new HashSet<Integer>(g.size());
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

        Set<Integer> g00 = new HashSet<>(g0.size());
        Set<Integer> g01 = new HashSet<>(g0.size());
        Set<Integer> g10 = new HashSet<>(g1.size());
        Set<Integer> g11 = new HashSet<>(g1.size());
        
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
        
        if( (!g00.isEmpty() && !g11.isEmpty() ) || (!g01.isEmpty() && !g10.isEmpty())) {
        	int r1 = (!g00.isEmpty() && !g11.isEmpty()) ? find(g00, g11, i-1) : 0;
            int r2 = (!g01.isEmpty() && !g10.isEmpty()) ? find(g10, g01, i-1) : 0;
            
            return Math.max(r1, r2);
        } else {
        	return find(g0, g1, i-1);
        }
    }

	public static void main(String[] args) {
//		int[] a = {3,10,5,25,2,8};
//		int[] a = {8,10,2};
		int[] a = {15,15,9,3,2};
		
		Solution s = new Solution();
		System.out.println(s.findMaximumXOR(a));
	}

}
