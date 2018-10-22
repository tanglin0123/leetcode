package leetcode.problem76.method1;

// misunderstanding the problem. 
class Solution {
    public String minWindow(String s, String t) {
        if(s.length() < t.length() || t.isEmpty()){
            return "";
        }
        
        if(t.length() == 1) {
            return s.contains(t) ? t : "";
        }
        
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        
        int slen = ss.length;
        int tlen = tt.length;
        
        int si = 0;
        int ti = 0;
        int ni = 0;
        
        int[] cur = new int[tlen];
        int[] next = new int[tlen];
        
        for(int i = 0; i < tlen; ++i) {
            cur[i] = -1;
            next[i] = -1;
        }
        
        int start = -1;
        int end = slen;
        
        while(si < slen) {
            if(ss[si] == tt[ti]){
                cur[ti++] = si;
                if(ti == tlen){
                    if(cur[tlen-1] - cur[0] < end - start) {
                        start = cur[0];
                        end = cur[tlen-1];

                        si = ni==0 ? si+1 : next[ni-1] + 1;
                        ti = ni;
                        ni = 0;
                        for(int i = 0; i < tlen; ++i){
                            cur[i] = next[i];
                            next[i] = -1;
                        }
                    }
                    continue;
                }
            } else if(ti == 1 && tt[0] != tt[1] && ss[si] == tt[0]){ 
                cur[0] = si;
            } 
            if(ss[si] == tt[ni] && ni != ti - 1 && next[ni] == -1) {
                next[ni++] = si;
            }
            
            ++si;
        }
        
        return start == -1 ? "" : s.substring(start, end + 1);
    }

	public static void main(String[] args) {
		String s = "ADOBECODEABNC";
		String t = "ABC";
		
		Solution sln = new Solution();
		
		System.out.println(sln.minWindow(s, t));
	}

}
