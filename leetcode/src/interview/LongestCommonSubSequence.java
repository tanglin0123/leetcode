package interview;

public class LongestCommonSubSequence {
	public int longestCommonSubSequence(String a, String b) {
		if(a == null || a.isEmpty() || b == null || b.isEmpty()) return 0;

		int n1 = a.length();
		int n2 = b.length();
		
		int[][] dp = new int[n1+1][n2+1];
		
		for(int i = 1; i < n1; ++i) {
			for(int j = 1; j < n2; ++j) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + 1;
				}
			}
		}
		
		return dp[n1][n2];
	}
	
}
