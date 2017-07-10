package leetcode.problem216.method1;

import java.util.*;

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> r = new ArrayList<List<Integer>>();
        
        List<Integer> cur = new ArrayList<Integer>();
        combination(n, 1, r, cur, k);
        
        return r;
    }
    
    public void combination(int target, int i, List<List<Integer>> r, List<Integer> cur, int k){
        if(target < 0){
            return;
        }
        
        if(target == 0 && cur.size() == k){
            r.add(new ArrayList<Integer>(cur));
            return;
        }
        
        // target > 0
        for(int j = i; j <= target && j <= 9; ++j){
            cur.add(j);
            
            combination(target - j, j+1, r, cur, k);
            
            cur.remove(cur.size()-1);
        }
    }


	public static void main(String[] args) {
		List<List<Integer>> r = new Solution().combinationSum3(3, 7);
		
		for(List<Integer> list: r){
			for(Integer i : list){
				System.out.print(i + ", ");
			}
			System.out.println();
		}
	}

}
