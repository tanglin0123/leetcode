package leetcode.problem3.method3;

import java.util.*;

// sliding window
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty()){
        	return 0;
        }
        
    	int size = s.length();
    	int start = 0; 
    	int end = 1;
    	int maxLen = 1;
    	int curLen = 1;
    	Map<Character, Integer> map = new HashMap<Character, Integer>();
    	map.put(s.charAt(0), 0);
    	
    	while(end < size){
    		char c = s.charAt(end);
    		
    		Integer pre = map.get(c);
    		if(pre == null){ // not found
    			curLen += 1;
    		} else { // found
    			start = pre+1;
    			curLen = end - start + 1;
    			Set<Character> toDel = new HashSet<Character>();
    			for(Map.Entry<Character, Integer> e: map.entrySet()){
    				if(e.getValue() <= pre){
    					toDel.add(e.getKey());
    				}
    			}
    			for(Character ch : toDel){
    				map.remove(ch);
    			}
    		}
    		
    		map.put(c, end);
    		
    		if(curLen > maxLen){
				maxLen = curLen;
			}
    		
    		++end;
    	}
    	
    	return maxLen;
    }
    
    public static void main(String[] args){
    	String s = "abcabcbb";
    	
    	Solution sln = new Solution();
    	System.out.println(sln.lengthOfLongestSubstring(s));
    }
}
