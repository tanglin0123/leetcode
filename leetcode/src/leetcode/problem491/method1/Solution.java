package leetcode.problem491.method1;

import java.util.*;

// wrong answer. should not change the order of the nums array
public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        if(nums == null || nums.length <= 1){
            return new LinkedList<List<Integer>>();
        }
        
        Map<Integer, Integer> numCounts = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; ++i){
        	Integer count = numCounts.get(nums[i]);
        	if(count == null){
        		count = 1;
        	} else {
        		++count;
        	}
        	numCounts.put(nums[i], count);
        }
        
        Object[] keys = numCounts.keySet().toArray();
        
        Arrays.sort(keys);
        
        return find(0, keys, numCounts);
        
    }
    
    private List<List<Integer>> find(int start, Object[] keys, Map<Integer, Integer> numCounts){
    	List<List<Integer>> r = new LinkedList<List<Integer>>();
    	
    	int cur = (Integer)keys[start];
    	int count = numCounts.get(keys[start]);
    	
    	if(count > 1){
    		List<Integer> selfPair = new LinkedList<Integer>();
			selfPair.add(cur);
			selfPair.add(cur);
			r.add(selfPair);
    	} 
    	
    	if(start == keys.length - 1){
    		return r;
    	}
    	
    	List<List<Integer>> r1 = find(start + 1, keys, numCounts);
    	
    	
    	for(List<Integer> item : r1 ){
    		List<Integer> newItem = new LinkedList<Integer>(item);
    		newItem.add(0, cur);
    		r.add(item);
    		r.add(newItem);
    	}
    	
    	for(int i = start + 1; i < keys.length; ++i){
    		List<Integer> item = new LinkedList<Integer>();
    		item.add(cur);
    		item.add((Integer)keys[i]);
    		r.add(item);
    	}
    	
    	return r;
    }
    
    public static void main(String[] args){
    	int[] nums = new int[]{4,6,7,7};
    	
    	Solution s = new Solution();
    	List<List<Integer>> r = s.findSubsequences(nums);
    
    	for(List<Integer> item : r){
    		System.out.print("[");
    		for(int i : item){
    			System.out.print(i + ", ");
    		}
    		System.out.print("], ");
    	}
    
    }
    
}