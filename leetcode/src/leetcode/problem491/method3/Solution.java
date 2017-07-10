package leetcode.problem491.method3;

import java.util.*;

// backtrace
public class Solution {
	
	
	public List<List<Integer>> findSubsequences(int[] nums) {
		Set<String> sSet = new HashSet<String>();
		ArrayList<Integer> current = new ArrayList<Integer>(15);
		
		List<List<Integer>> r = new LinkedList<List<Integer>>();
		
		find(0, nums, sSet, r, current);
		
		return r;
	}
	
	private void find(int start, int[] nums, Set<String> sSet, List<List<Integer>> r, ArrayList<Integer> current){
		for(int i = start; i < nums.length; ++i){
			if(current.isEmpty() || nums[i] >= current.get(current.size() - 1 )){
				current.add(nums[i]);
				if(current.size() >= 2){
					String s = toString(current);
					if(!sSet.contains(s)){
						sSet.add(s);
						r.add(new ArrayList<Integer>(current));
					}
				}
				
				find(i + 1, nums, sSet, r, current);
				
				current.remove(current.size() - 1);
			}
		}
	}
	
	private String toString(List<Integer> l){
		String r = "";
		for(int s : l){
			r+=s+"_";
		}
		return r;
	}
	
    public static void main(String[] args){
    	int[] nums = new int[]{4,6,7,7};
    	//int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10,1,1,1,1,1};
    	
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
