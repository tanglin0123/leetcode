package leetcode.problem375.method2;

//wrong idea
public class Solution {
	public int getMoneyAmount(int n) {
		int[][] dp = new int[n+1][n+1];
		
		for(int len = 2; len <= n; ++len ){
			for(int i = 1; i+len-1 <= n; ++i){
				dp[i][i+len-1] = Integer.MAX_VALUE;
				for(int j = i; j<= i+len-1; ++j){
					int sum = j;
					
					int left =0;
					if(j!=i){
						left = dp[i][j-1]; 
					}
					
					int right = 0;
					if(j!=i+len-1){
						right = dp[j+1][i+len-1];
					}
					
					sum += Math.max(left, right);
					
					if(sum < dp[i][i+len-1]){
						dp[i][i+len-1] = sum;
					}
				}
			}
		}
		
		this.outputDP(dp);
		
		return dp[1][n];
	}

	private void outputDP(int[][] dp){
		for(int[] dp1: dp){
			for(int d : dp1){
				System.out.print(d+", ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		//int i = 4;
		
		int i = 7;
		
		Solution s = new Solution();
		System.out.println(s.getMoneyAmount(i));

	}

}

