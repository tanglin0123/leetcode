package leetcode.problem329.method1;

import java.util.*;

// BFS. accept but slow
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if(m == 0){
            return 0;
        }
        
        int n = matrix[0].length;
        if(n == 0){
            return 0;
        }
        
        Set<String> set = new HashSet<>();
        
        int max = 0;
        
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                set.add(i + "," + j);
            }
        }
        
        while(!set.isEmpty()){
            ++max;
            
            Set<String> newset = new HashSet<>();
            
            for(String s : set){
                String[] ss = s.split(",");
                int r = Integer.parseInt(ss[0]);
                int c = Integer.parseInt(ss[1]);
                int cur = matrix[r][c];
                
                if(r-1>=0 && matrix[r-1][c] > cur){
                    newset.add((r-1) + "," + c);
                }
                if(r+1<m && matrix[r+1][c] > cur){
                    newset.add((r+1) + "," + c);
                }
                if(c-1>=0 && matrix[r][c-1] > cur){
                    newset.add(r + "," + (c-1));
                }
                if(c+1<n && matrix[r][c+1] > cur){
                    newset.add(r + "," + (c+1));
                }
            }
            
            set = newset;
        }
        
        return max;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
