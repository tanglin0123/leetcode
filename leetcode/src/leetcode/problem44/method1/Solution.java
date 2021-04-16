package leetcode.problem44.method1;

public class Solution {
	public boolean isMatch(String s, String p) {
		int slen = s.length();
		int plen = p.length();
		
		if(plen == 0 && slen > 0) {
			return false;
		}
		
		boolean[][] dp = new boolean[slen + 1][plen + 1];
		
		dp[0][0] = true;
		
		for(int si = 1; si <= slen; ++si) {
			dp[si][0] = false;
		}
		
		for(int pi = 1; pi <= plen; ++pi) {
			for(int si = 0; si <= slen; ++si) {
				char pc = p.charAt(pi - 1);
				
				if(pc == '*') {
					if(dp[si][pi-1]) {
						dp[si][pi] = true;
					} else {
						boolean match = false;
						for(int i = si; i >= 0; --i) {
							if(dp[i][pi-1]) {
								match = true;
								break;
							}
						}
						
						if(!match) {
							dp[si][pi] = false;
						} else {
							dp[si][pi] = true;
						}
					}
				} else {
					if(si - 1 >= 0) {
						char sc = s.charAt(si - 1);
						dp[si][pi] = dp[si-1][pi-1] && charMatch(sc, pc);
					} else {
						dp[si][pi] = false;
					}
				}
			}
		}
	
		return dp[slen][plen];
	}
	
	boolean charMatch(char sc, char pc) {
		return pc == '?' || pc == sc;
	}
	
	public static void main(String[] args) {
//		String s = "aa";
//		String p = "a";
		
		String s = "adceb";
		String p = "*a*b";
		
//		String s = "";
//		String p = "**";
		
		Solution sln = new Solution();
		
		System.out.println(sln.isMatch(s, p));

	}

}
