package leetcode.problem424.method1;

public class Solution {
    
    public int characterReplacement(String s, int k) {
        if(s == null || s.isEmpty()){
            return 0;
        }
        
        if(s.length() <= k){
            return s.length();
        }
        
        char[] chs = s.toCharArray();
        
        int[] counts = new int[26];
        
        char domChar = chs[0];
        int domCharCnt = 1;
        int len = 1;
        
        int max = 0;
        
        counts[domChar-'A'] = 1;
        
        for(int i = 1; i < chs.length; ++i ){
            char c = chs[i];
            int cnt = ++counts[c-'A'];
            
            ++len;
            
            if(c == domChar){ // remain dominant
                domCharCnt = cnt;
                
            } else{ // may change dominant
                
                if(cnt > domCharCnt){ // new char became dominant
                    domChar = c;
                    domCharCnt = cnt;
                    
                } else { // dominance may not change for now
                
                    if(len - domCharCnt > k){ // need trim window and recalculate dominance
                    	
                    	// clear all
                    	
                    	counts = new int[26]; 
                    	
                    	domChar = c;
                        domCharCnt = 1;
                        counts[domChar - 'A'] = 1;
                        
                        int start = i - len + 1;
                        
                        len = 1;
                        
                        // count back, because need to include the current one
                    	for(int j = i-1; j >= start; --j){
                    		c = chs[j];
                        	
                    		cnt = ++counts[c-'A'];
                    		
                    		++len;
                    		
                    		if(c == domChar){
                    			domCharCnt++;
                    		} else{
                    			if(cnt > domCharCnt){
                    				domChar = c;
                    				domCharCnt = cnt;
                    			} else {
                    				if(len - domCharCnt > k){
                    					--len;
                    					--counts[c-'A'];
                    					break;
                    				}
                    			}
                    		}
                        }
                    	
                    }    
                }
            }
            
            if(len > max ){
                max = len;
            }
        }
        
        return max;
    }


	public static void main(String[] args) {
//		String a = "EOEMQLLQTRQDDCOERARHGAAARRBKCCMFTDAQOLOKARBIJBISTGNKBQGKKTALSQNFSABASNOPBMMGDIOETPTDICRBOMBAAHINTFLH";
//		int b = 7;
		
//		String a = "AABA";
//		int b = 0;
		
//		String a = "AABABBA";
//		int b = 1;
		
		String a = "ABAA";
		int b = 0;
		
		Solution s = new Solution();
		System.out.println(s.characterReplacement(a, b));
	}

}
