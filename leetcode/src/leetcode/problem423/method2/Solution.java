package leetcode.problem423.method2;

import java.util.*;

// learned from answers
public class Solution {
    //String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    char[] keys = {'z', 'w', 'u', 'x', 'g', 'o', 'h', 'f', 's', 'i'};
    String[] nums = {"zero", "two", "four", "six", "eight", "one", "three", "five", "seven", "nine"};
    int[] pos = {0, 2, 4, 6, 8, 1, 3, 5, 7, 9}; 
    		
    public String originalDigits(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for(char c : s.toCharArray()){
            Integer i = map.get(c);
            if(i == null){
                i = 1;
            } else {
                i += 1;
            }
            
            map.put(c, i);
        }
        
        int[] counts = new int[10]; 
        for(int i = 0; i < keys.length; ++i ){
        	Integer n = map.get(keys[i]);
        	if(n == null || n == 0){
        		continue;
        	}
        	
        	counts[pos[i]] += n;
        	
        	for(char c : nums[i].toCharArray() ){
                Integer cnt = map.get(c) - n;
                map.put(c, cnt);
            }
        }
        
        String str = "";
        for(int i = 0; i < counts.length; ++i){
        	for(int j = 0; j < counts[i]; ++j){
        		str+=i;
        	}
        	
        }
        return str;
    }   

	public static void main(String[] args) {
		//String a = "owoztneoer";
		//String a = "fviefuro";
		
		String a = "nnei";
		
		Solution s = new Solution();
		System.out.println(s.originalDigits(a));

	}

}
