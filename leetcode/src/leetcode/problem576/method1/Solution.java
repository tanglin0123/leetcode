package leetcode.problem576.method1;

class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        int[][][] count = new int[m][n][N+1];
        boolean[][][] done = new boolean[m][n][N+1];
        
        long sum = 0;
        
        for(int k = 1; k <= N; ++k){
            sum = (sum + findExactPaths(m, n, k, i, j, count, done) % 1000000007L) % 1000000007L;
        }
        
        return (int)sum;
    }
    
    public int findExactPaths(int m, int n, int N, int i, int j, int[][][] count, boolean[][][] done) {
        if(N == 0)
            return 0;
        
        if(i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        
        if(done[i][j][N]) {
            return count[i][j][N];
        }
        
        if(N == 1){
            if(i == 0){
                count[i][j][N]++;
            }
            if(i == m-1) {
                count[i][j][N]++;
            }
            if(j == 0) {
                count[i][j][N]++;
            }
            if(j == n-1){
                count[i][j][N]++;
            }
            done[i][j][N] = true;
            
            return count[i][j][N]; 
        }
        
        long sum = (findExactPaths(m, n, N-1, i+1, j, count, done) % 1000000007L + 
                findExactPaths(m, n, N-1, i-1, j, count, done) % 1000000007L + 
                findExactPaths(m, n, N-1, i, j+1, count, done) % 1000000007L + 
                findExactPaths(m, n, N-1, i, j-1, count, done) % 1000000007L) % 1000000007L;
        
        count[i][j][N] = (int)sum;
        done[i][j][N] = true;
            
        return (int)sum;
    }
    
	public static void main(String[] args) {
		//int m = 2, n = 2, N = 2, i = 0, j = 0; // 6
//		int m = 1, n = 3, N = 3, i = 0, j = 1; // 12
		int m = 1, n = 2, N = 50, i = 0, j = 0; // 150
		
		Solution s = new Solution();
		System.out.println(s.findPaths(m, n, N, i, j));
	}

}
