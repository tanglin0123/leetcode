package leetcode.problem132.method2;

public class Solution {
    public int minCut(String s) {
        if(s==null || s.isEmpty() || s.length() == 1){
            return 0;
        }
        
        char[] chs = s.toCharArray();
        
        return minCut(chs, 0, chs.length-1);
        
    }
    
    int minCut(char[] chs, int start, int end){
    	int min = Integer.MAX_VALUE;
    	
    	if(isPalindrome(start, end, chs)){
    		return 0;
    	}
    	
    	for(int i = start; i <= end; ++i){
    		if(isPalindrome(start, i, chs)){
    			int curMinCut;
    			if(i == end){
    				curMinCut = 0;
    			} else {
    				curMinCut = 1+minCut(chs, i+1, end);
    			}
    			if(min>curMinCut){
    				min=curMinCut;
    			}
    		}
    	}
    	
    	return min;
    }
    
    
    boolean isPalindrome(int start, int end, char[] chs){
        for(int i = start, j = end; i<j; ++i, --j){
            if(chs[i] != chs[j]){
                return false;
            }
        }
        return true;
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();
		String str = "fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi";
		//String str = "aab";
		//String str = "acaab";
		System.out.println(s.minCut(str));
	}

}
