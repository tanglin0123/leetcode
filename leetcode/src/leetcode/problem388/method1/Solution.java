package leetcode.problem388.method1;

import java.util.*;

public class Solution {
    static class FileName{
        int tabNum = 0;
        int fullNameLen = 0;
        
        FileName(int tabNum, int fullNameLen){
            this.tabNum = tabNum;
            this.fullNameLen = fullNameLen;
        }
    }
    
    public int lengthLongestPath(String input) {
        if(input == null || input.isEmpty()){
            return 0;
        }
        
        Stack<FileName> stk = new Stack<FileName>();
        
        String[] files = input.split("\n");
        
        int maxLen = 0;
        
        for(int i = 0; i < files.length; ++i){
            String f = files[i];
            
            int tabNum = f.lastIndexOf("\t") + 1;
            
            int parLen = 0;
            
            while(!stk.isEmpty() && stk.peek().tabNum >= tabNum){
                stk.pop();
            }
            if(!stk.isEmpty()){
                parLen = stk.peek().fullNameLen;
            }
            
            int fullLen = parLen + (parLen == 0? 0:1) + (f.length() - tabNum);
            
            if(f.contains(".")){ // file
                if(fullLen > maxLen){
                    maxLen = fullLen;
                }
            } else {
                FileName now = new FileName(tabNum, fullLen);
                stk.push(now);
            }
        }
        
        return maxLen;
    }

	public static void main(String[] args) {
		String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
		
		Solution s = new Solution();
		
		System.out.println(s.lengthLongestPath(input));
		
		System.out.println(input);
		

	}

}
