package leetcode.problem10.method2;

// dp
public class Solution {
	public boolean isMatch(String s, String p) {
		int slen = s.length();
		int plen = p.length();
		
		if(slen == 0 && plen == 0) {
			return true;
		}

		if(slen > 0 && plen == 0) {
			return false;
		}
		
		if(plen == 1) {
			if(slen != 1) 
				return false; 
			return this.matchChar(s.charAt(0), p.charAt(0));
		}
		
		boolean[][] dp = new boolean[slen + 1][plen + 1];

		dp[0][0] = true;
		dp[0][1] = false;
		
		for (int si = 1; si <= slen; ++si) {
			dp[si][0] = false;
		}
		 
		for (int pi = 2; pi <= plen; ++pi) {
			char pc = p.charAt(pi-1);
			if(pc != '*') {
				dp[0][pi] = false;
			} else {
				dp[0][pi] = dp[0][pi-2];
			}
			
		}
		
		for(int si = 1; si <= slen; ++si) {
			char sc = s.charAt(si-1);
			for(int pi = 1; pi <= plen; ++pi) {
				char pc = p.charAt(pi-1);
				if(pc != '*') {
					dp[si][pi] = dp[si-1][pi-1] && matchChar(sc, pc);
				} else { // pc == '*'
					char pc1 = p.charAt(pi-2);
					if(matchChar(sc, pc1)) {
						dp[si][pi] = dp[si][pi-1] || dp[si][pi-2] || dp[si-1][pi];
					} else {
						dp[si][pi] = dp[si][pi-2];
					}
				}
			}
		}

		return dp[slen][plen];
	}
	
	boolean matchChar(char sc, char pc) {
		return pc == '.' || sc == pc;
	}
	
	public static void main(String[] args) {
		String s = "aa";
		String p = "a*";
		
		Solution sln = new Solution();
		
		System.out.println(sln.isMatch(s, p));
	}
	
}
