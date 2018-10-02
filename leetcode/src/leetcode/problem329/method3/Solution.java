package leetcode.problem329.method3;

import java.util.*;

// dfs
public class Solution {
	int helper(int[][] matrix, int[][] cache, int curmax, int r, int c) {
        int m = matrix.length;
        int n = matrix[0].length;
        
		int cur = matrix[r][c];
        
		int max = curmax;
        if(r-1>=0 && matrix[r-1][c] > cur && cache[r-1][c] < curmax + 1){
        	max = Math.max(max, helper(matrix, cache, curmax+1, r-1, c));
            cache[r-1][c] = curmax + 1;
        }
        if(r+1<m && matrix[r+1][c] > cur && cache[r+1][c] < curmax + 1){
        	max = Math.max(max, helper(matrix, cache, curmax+1, r+1, c));
            cache[r+1][c] = curmax + 1;
        }
        if(c-1>=0 && matrix[r][c-1] > cur && cache[r][c-1] < curmax + 1){
        	max = Math.max(max, helper(matrix, cache, curmax+1, r, c-1));
            cache[r][c-1] = curmax + 1;
        }
        if(c+1<n && matrix[r][c+1] > cur && cache[r][c+1] < curmax + 1){
        	max = Math.max(max, helper(matrix, cache, curmax+1, r, c+1));
            cache[r][c+1] = curmax + 1;
        }
        
        return max;
	}
	
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if(m == 0){
            return 0;
        }
        
        int n = matrix[0].length;
        if(n == 0){
            return 0;
        }
        
        int[][] cache = new int[m][n];
        
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                cache[i][j] = 1;
            }
        }
        
        int max = 0;
        
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                max = Math.max(max, helper(matrix, cache, 1, i, j));
            }
        }
        
        return max;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
