package leetcode.problem329.method2;

import java.util.*;

// optimized BFS
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
        
        LinkedList<int[]> q = new LinkedList<>();
        int[][] cache = new int[m][n];
        
        int max = 0;
        
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                q.add(new int[] {i, j});
                cache[i][j] = 1;
            }
        }
        
        while(!q.isEmpty()){
            ++max;
            
            int qlen = q.size();
            
            for(int i = 0; i < qlen; ++i) {
            	int[] pos = q.removeFirst();
            	int r = pos[0];
                int c = pos[1];
                int cur = matrix[r][c];
                
                if(r-1>=0 && matrix[r-1][c] > cur && cache[r-1][c] < max + 1){
                    q.add(new int[] {r-1, c});
                    cache[r-1][c] = max + 1;
                }
                if(r+1<m && matrix[r+1][c] > cur && cache[r+1][c] < max + 1){
                	q.add(new int[] {r+1, c});
                    cache[r+1][c] = max + 1;
                }
                if(c-1>=0 && matrix[r][c-1] > cur && cache[r][c-1] < max + 1){
                	q.add(new int[] {r, c-1});
                    cache[r][c-1] = max + 1;
                }
                if(c+1<n && matrix[r][c+1] > cur && cache[r][c+1] < max + 1){
                	q.add(new int[] {r, c+1});
                    cache[r][c+1] = max + 1;
                }
            }
        }
        
        return max;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
