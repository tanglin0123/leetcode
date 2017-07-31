package interview;

import java.util.*;

// from yangrui
public class NStrings {
	// given n strings(or arrays), each element is of 0~5. 
	// calculate how many permutations to form a new string with the rules:
	// 1) get one char from each string from the n strings
	// 2) no duplicate chars in the new string
	public int count_bt(int[][] in){
		Set<Integer>[] sets = new HashSet[in.length];
		
		for(int i = 0; i < in.length; ++i){
			sets[i] = new HashSet<Integer>();
			for(int j : in[i]){
				sets[i].add(j);
			}
		}
		
		boolean[] used = new boolean[6];
		
		return backtrace(sets, 0, used);
	}
	
	private int backtrace(Set<Integer>[] sets, int i, boolean[] used){
		if(i == sets.length){
			return 1;
		}
		
		int sum = 0;
		for(int num : sets[i]){
			if(used[num]){
				continue;
			}
			
			used[num] = true;
			List<Integer> rmlist = new ArrayList<Integer>(sets.length - i + 1);
			for(int j = i+1; j < sets.length; ++j ){
				if(sets[j].contains(num)) {
					rmlist.add(j);
					sets[j].remove(num);	
				}
			}
			
			sum += backtrace(sets, i+1, used);
			
			used[num] = false;
			for(int j : rmlist){
				sets[j].add(num);
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
//		int[][] a = {{1,2}, {3,4}};
		
		int[][] a = {{1,0},{0,1}};
		
		NStrings n = new NStrings();
		System.out.println(n.count_bt(a));
	}
}
