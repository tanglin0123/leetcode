package leetcode.problem10.method3;

public class Solution {
	public boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }
	
	private boolean charMatch(char sc, char pc) {
		return sc == pc || pc == '.';
	}
	
	private boolean isMatch(String s, String p, int si, int pi) {
		
		if(pi == p.length()) {
			if(si == s.length()) {
				return true;
			} else {
				return false;
			}
		}
		
		if(si == s.length()) {
			if(pi + 1 >= p.length()) {
				return false;
			}
			
			char pc = p.charAt(pi+1);
			if(pc == '*') {
				return isMatch(s, p, si, pi + 2);
			} else {
				return false;
			}
		}
		
		
		char sc = s.charAt(si);
		char pc = p.charAt(pi);
		
		if(pi+1 < p.length() && p.charAt(pi+1) == '*') {
			if(charMatch(sc, pc)) {
				return isMatch(s, p, si+1, pi) || isMatch(s, p, si, pi+2);
			} else {
				return isMatch(s, p, si, pi+2);
			}
		} else {
			return charMatch(sc, pc) && isMatch(s, p, si+1, pi+1);
		}
    }
	
	public static void main(String[] args) {
		String s = "a";
		String p = ".*..a*";
		
		Solution sln = new Solution();
		
		System.out.println(sln.isMatch(s, p));
	}

}
