package interview;

import java.util.*;

// from yangrui
public class NStrings {
	
	public int count_bt(int[][] in){
		Set<Integer>[] sets = new HashSet[in.length];
		
		for(int i = 0; i < in.length; ++i){
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
			for(int j = i+1; j < sets.length; ++j ){
				sets[j].remove(num);
			}
			sum += backtrace(sets, i+1, used); 
		}
		
		
	}
}
