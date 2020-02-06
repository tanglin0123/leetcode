package leetcode.problem10.method1;

// recursive way, pass with only win 0.97%
public class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }
    
    private boolean isMatch(String s, String p, int si, int pi) {
        if(si == s.length()) {
            if(pi == p.length()) {
                return true;
            } else if(pi > p.length()) {
                return false;
            } else {
                if((p.length() - pi) % 2 != 0) {
                    return false;
                }
                for(int i = pi+1; i < p.length(); i += 2) {
                    if(p.charAt(i) != '*') {
                        return false;
                    }
                }
                return true;
            }
        } 
        
        // si < s.length()
        if(pi >= p.length()) {
            return false;
        }
        
        char sc = s.charAt(si);
        char pc = p.charAt(pi);
        
        if(sc == pc || pc == '.') {
            if(pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
                return isMatch(s, p, si + 1, pi) || isMatch(s, p, si + 1, pi + 2) || isMatch(s, p, si, pi + 2);
            } else {
                return isMatch(s, p, si + 1, pi + 1);
            }
        } else {
            if(pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
                return isMatch(s, p, si, pi + 2);
            } else {
                return false;
            }
        }
    }

}
