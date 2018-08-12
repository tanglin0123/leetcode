package interview;


// https://www.interviewbit.com/problems/unique-paths-in-a-grid/
public class UniquePathsInAGrid { 

	public int findPaths(int[][] grid) {
		int m = grid.length;
		if(m == 0) {
			return 0;
		}
		int n = grid[0].length;
		if(n == 0) {
			return 0;
		}
		
		int[][] dp = new int[m][n];
		dp[0][0] = 1;
		
		for (int j = 1; j < n; ++j) {
			if (grid[0][j] == 1) {
				dp[0][j] = 0;
			} else {
				dp[0][j] = dp[0][j - 1];
			}
		}
		
		for(int i = 1; i < m; ++i) {
			for(int j = 0; j < n; ++j) {
				if(grid[i][j] == 1) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = (i-1<0 ? 0 : dp[i-1][j]) + (j-1<0 ? 0 : dp[i][j-1]);
				}
			}
			
		}
		
		return dp[m-1][n-1];
	}
	
	public static void main(String[] args) {
		int[][] grid = {
		                {0,0,0},
		                {0,1,0},
		                {0,0,0}
		                };
		
		UniquePathsInAGrid u = new UniquePathsInAGrid();
		System.out.println(u.findPaths(grid));

	}

}
