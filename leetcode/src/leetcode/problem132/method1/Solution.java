package leetcode.problem132.method1;

import java.util.*;

public class Solution {
    public int minCut(String s) {
        if(s==null || s.isEmpty() || s.length() == 1){
            return 0;
        }
        
        List<List<Integer>> q = new LinkedList<List<Integer>>();
        char[] chs = s.toCharArray();
        
        List<Integer> r = new LinkedList<Integer>();
        r.add(0);
        q.add(r);
        while(!q.isEmpty()){
            List<Integer> r0 = q.remove(0);
            System.out.println(r0.size());
            int lastStart = r0.get(r0.size()-1);
            if(isPalindrome(lastStart, chs.length-1, chs)){
            	int i = 0;
            	
            	while(i< r0.size()){
            		int start=r0.get(i);
            		int start2=-1;
            		if(i != r0.size()-1){
            			start2 = r0.get(i+1); 
            		}
            		if(start2!=-1){
            			System.out.println(new String(chs, start, start2-start));
            		} else{
            			System.out.println(new String(chs, start, chs.length - start));
            		}
            		
            		++i;
            	}
            	
                return r0.size()-1;
            } else{
                for(int i = lastStart; i< chs.length-1; ++i){
                    if(isPalindrome(lastStart, i, chs)){
                        List<Integer> r1 = new LinkedList<Integer>(r0);
                        r1.add(i+1);
                        q.add(r1);
                    }
                }
            }
            
        }
        return chs.length-1;
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
