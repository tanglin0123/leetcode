package leetcode.problem115.method1;

class Solution {
    public int numDistinct(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        
        if(slen < tlen){
            return 0;
        }
        
        int[][] dp = new int[tlen+1][slen+1];
        
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        
        for(int i = 0; i <= tlen; ++i){
            dp[i][0] = 0;
        }
        
        for(int j = 0; j <= slen; ++j){
            dp[0][j] = 0;
        }
        
        // i = 1
        for(int j = 1; j <= slen - tlen + 1; ++j){
            dp[1][j] = tt[0] == ss[j-1] ? dp[1][j-1] + 1 : dp[1][j-1];
        }
        
        for(int j = slen - tlen + 2; j <= slen; ++j){
            dp[1][j] = dp[1][j-1];
        }
        
        if(dp[1][slen] == 0){
            return 0;
        }
        
        for(int i = 2; i <= tlen; ++i){
            for(int j = 1; j <= slen - tlen + i; ++j){
                dp[i][j] = tt[i-1] == ss[j-1] ? dp[i][j-1] + dp[i-1][j-1] : dp[i][j-1];
            }
            
            for(int j = slen - tlen + i + 1; j <= slen; ++j){
                dp[i][j] = dp[i][j-1];
            }
            
            if(dp[i][slen] == 0){
                return 0;
            }
        }
        
        return dp[tlen][slen];
    }
    
	public static void main(String[] args) {
		String s = "rabbbit";
		String t = "rabbit";
		
		Solution sln = new Solution();
		
		System.out.println(sln.numDistinct(s, t));

	}

}
