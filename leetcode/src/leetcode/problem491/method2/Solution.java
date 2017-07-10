package leetcode.problem491.method2;

import java.util.*;

// wrong solution. do not waste more time on it
public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        if(nums == null || nums.length <= 1){
            return new LinkedList<List<Integer>>();
        }
        
        TreeMap<Integer, Integer> numCounts = new TreeMap<Integer, Integer>();
        
        return find(0, nums, numCounts);
        
    }
    
    private List<List<Integer>> find(int start, int[] nums, TreeMap<Integer, Integer> numCounts){
    	if(start == nums.length - 1){
    	    numCounts.put(nums[start], 1);
    		return new LinkedList<List<Integer>>();
    	}
    	
    	
    	List<List<Integer>> afterCur = find(start + 1, nums, numCounts);
    	List<List<Integer>> r = new LinkedList<List<Integer>>(afterCur);
    	
    	int cur = nums[start];
    	Integer count = numCounts.get(cur);
    	
    	if(count == null){
    	    count = 1;
    	} else {
    		++count;
    	}

    	if(count == 2){
    		List<Integer> selfSeq = new LinkedList<Integer>();
    		for(int i = 0; i < count; ++i){
    			selfSeq.add(cur);
    		}
			r.add(selfSeq);
    	} 
    	
    	boolean successive = false;
    	for(List<Integer> item : afterCur){
    		if(item.get(0) > cur && count == 1){
    			List<Integer> newItem = new LinkedList<Integer>(item);
        		newItem.add(0, cur);
        		r.add(newItem);
    		} else if(item.get(0) == cur){
    			if(item.size() >= count-1 && item.get(count-2) == cur){
        			List<Integer> newItem = new LinkedList<Integer>(item);
            		newItem.add(0, cur);
            		r.add(newItem);
    			}
    			successive = true;
    		}
    	}
    	
    	if(!successive){
    		Set<Integer> keySet = numCounts.keySet();
    		for(int i : keySet){
        		if(i > cur){
        			List<Integer> newItem = new LinkedList<Integer>();
            		newItem.add(cur);
            		newItem.add(i);
            		r.add(newItem);
        		}
        	}
    	}
    	
    	
    	numCounts.put(cur, count);
    	
    	return r;
    }
    
    public static void main(String[] args){
    	//int[] nums = new int[]{4,6,7,7};
    	int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10,1,1,1,1,1};
    	
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