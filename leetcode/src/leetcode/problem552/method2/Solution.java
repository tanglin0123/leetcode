package leetcode.problem552.method2;


// dp
public class Solution {
    public int checkRecord(int n) {
    		int M = 1000000007;
    	
    		int[][][] dp = new int[n+1][2][3];
    		dp[1][0][0] = 1; // p=1
    		dp[1][1][0] = 1; // a=1
    		dp[1][0][1] = 1; // l=1
    		
    		for(int i = 2; i <= n; ++i) {
    			dp[i][0][0] = ((dp[i-1][0][0] + dp[i-1][0][1] ) % M + dp[i-1][0][2]) % M; 
    			dp[i][0][1] = dp[i-1][0][0];
    			dp[i][0][2] = dp[i-1][0][1];
    			dp[i][1][0] = (((dp[i-1][1][0] + dp[i-1][0][0]) % M + (dp[i-1][0][1] + dp[i-1][0][2]) % M) % M + (dp[i-1][1][1] + dp[i-1][1][2]) % M) % M; 
    			dp[i][1][1] = dp[i-1][1][0];
    			dp[i][1][2] = dp[i-1][1][1];
    		}
    		
    		int sum = 0;
    		for(int i = 0; i < 2; ++i) {
    			for(int j = 0; j < 3; ++j) {
    				sum = (sum + dp[n][i][j]) % M;
    			}
    		}
    		
    		return sum;
    }

	public static void main(String[] args) {
		// int a = 2;
		int a = 4;
		
		Solution s = new Solution();
		System.out.println(s.checkRecord(a));

	}

}
