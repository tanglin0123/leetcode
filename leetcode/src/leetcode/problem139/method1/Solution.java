package leetcode.problem139.method1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        if(s==null || s.isEmpty()){
            return false;
        }
        
        char[] chs = s.toCharArray();
        
        int[][] isBreakable = new int[chs.length][];
        for(int i = 0; i < chs.length; ++ i){
        	isBreakable[i] = new int[chs.length];
        }
        
        int maxWord = 0;
        for(String word : wordDict){
        	if(word.length() > maxWord){
        		maxWord = word.length();
        	}
        }
        
        return wordBreak(chs, 0, wordDict, isBreakable, maxWord);
    }
    
    public boolean wordBreak(char[] chs, int start, Set<String> wordDict, int isBreakable[][], int maxWord){
    	String s = new String(chs, start, chs.length - start);
//    	System.out.println(s);
    	
    	if( isBreakable[start][chs.length-1] == 1){
            return true;
        } else if(isBreakable[start][chs.length-1] == -1){
        	return false;
        } else { // isBreakable = 0
        	if(wordDict.contains(s)){
        		isBreakable[start][chs.length-1]=1;
        		return true;
        	} 
        	// else continue next steps
        }
        
        for(int i = start; i < chs.length - 1 && i <= start + maxWord - 1 ; ++i){
            if(isBreakable[start][i] == -1){
            	continue;
            }
        	
            String first = new String(chs, start, i - start + 1);
            
            if(isBreakable[start][i] == 0){
            	if(!wordDict.contains(first)){
                	isBreakable[start][i] = -1;
                    continue;
                } else {
                	isBreakable[start][i] = 1;
                }
            }
            
//            System.out.println("first: " + first);
            
            if(wordBreak(chs, i + 1, wordDict, isBreakable, maxWord)){
            	isBreakable[i+1][chs.length-1] = 1;
                return true;
            } else {
            	isBreakable[i+1][chs.length-1] = -1;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args){
    	String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"; 
    	String[] ss = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
    	
//    	String s = "leetcode";
//    	String[] ss = {"leet", "code"};
    	
    	Set<String> wordDict = new HashSet<String>();
    	for(String sss: ss){
    		wordDict.add(sss);
    	}
    	
    	System.out.println(new Solution().wordBreak(s, wordDict));
    }
}
