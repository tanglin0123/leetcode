package leetcode.problem406.method1;

import java.util.*;

public class Solution {
    
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] > o2[0]){
					return -1;
				} else if(o1[0] < o2[0]){
					return 1;
				} else{ // o1[0] == o2[0]
					if(o1[1] > o2[1]){
						return 1;
					} else { // o1[1] < o2[1]
						return -1;
					}
				}
			}

            
        })  ;  
        
        ArrayList<int[]> r = new ArrayList<int[]>();
        
        for(int[] p : people){
        	r.add(p[1], p);
        }
        
        return r.toArray(people);
    }

    
	public static void main(String[] args) {
		int[][] a = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
		
		Solution s = new Solution();
		a = s.reconstructQueue(a);
		for(int[] p: a){
			System.out.print("[");
			for(int i: p){
				System.out.print(i+",");
			}
			System.out.print("],");
		}
	}

}
