package leetcode.problem395.method1;

class Solution {
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() < k || k <= 0){
            return 0;
        }
        return helper(0, s.length()-1, s, k);
    }
    
    public int helper(int start, int end, String s, int k){
        if(end - start + 1 < k){
            return 0;
        }
        
        int[] count = new int[26];
        
        for(int i = start; i <= end; ++i){
            char c = s.charAt(i);
            ++count[c-'a'];
        }
        
        int mincnt = Integer.MAX_VALUE;
        for(int i : count) {
        	if(i == 0) {
        		continue;
        	}
        	mincnt = Math.min(mincnt, i);
        }
        
        if(mincnt >= k) {
        	return end - start + 1;
        }
        
        int h = start;
        int max = 0;
        for(int i = start; i <= end; ++i){
            char c = s.charAt(i);
            
            if(count[c-'a'] < k){
                max = Math.max(max, helper(h, i-1, s, k));
                h = i + 1;
            }    
        }
        
        if(count[s.charAt(end)-'a'] >= k) {
        	max = Math.max(max, helper(h, end, s, k));
        }
        
        return max;
    }

	public static void main(String[] args) {

		String a = "bbaaacbd";
		int b = 3;
		
		Solution s = new Solution();
		System.out.println(s.longestSubstring(a, b));
	}

}
