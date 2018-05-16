package leetcode.problem646.method1;

import java.util.*;

class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[1] < b[1]) return -1;
                if(a[1] > b[1]) return 1;
                return a[0] - b[0];
            }
        });
        
        int len = 1;
        int end = pairs[0][1];
        
        for(int i = 1; i < pairs.length; ++i){
        	int[] p = pairs[i];
        	if(end < p[0]) {
        		++len;
        		end = p[1];
        	}
        }
        
        return len;
    }
    

	public static void main(String[] args) {
		int[][] pairs = {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
//		int[][] pairs = {{7,9},{4,5},{7,9},{-7,-1},{0,10},{3,10},{3,6},{2,3}};
		
		Solution s = new Solution();
		System.out.println(s.findLongestChain(pairs));

	}

}
