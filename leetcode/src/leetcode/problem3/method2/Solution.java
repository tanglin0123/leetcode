package leetcode.problem3.method2;

// timeout
public class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0){
            return 0;
        }
        
        if(s.length()==1){
            return 1;
        }
        
        char[] chs = s.toCharArray();
        
        int[][] longest = new int[chs.length][2];
        
        longest[chs.length -1][0] = chs.length-1;
        longest[chs.length -1][1] = chs.length-1;
        
        for(int i = chs.length-2; i >= 0; --i){
        	boolean[] visited = new boolean[65536];
        	
        	int[] maxPos = new int[2];
        	maxPos[0] = i;
        	maxPos[1] = i;
        	int max = 0;
        	for(int j = i; j < chs.length; ++j){
        		char c = chs[j];
        		if(visited[c]){
        			int curMax = j - i;
        			int surMax = longest[j][1] - longest[j][0] + 1;
        			
        			if(curMax > max || surMax > max){
        			    if(curMax > surMax){
        			        maxPos[0] = i;
        				    maxPos[1] = j-1;
        				    max = curMax;
        				
        			    } else {
        				    maxPos[0] = longest[j][0];
        				    maxPos[1] = longest[j][1];
        				    max = surMax;
        			    }
        			}
        			break;
        		} else{
        			visited[c] = true;
        			int curMax = j - i + 1;
        			int surMax = j < chs.length-1 ?  longest[j+1][1] - longest[j+1][0] + 1: 0;
        			
        			if(curMax > max || surMax > max){
        			    if(curMax > surMax){
        				    maxPos[0] = i;
        				    maxPos[1] = j;
        				    max = curMax;
        			    } else {
        				    maxPos[0] = longest[j+1][0];
        				    maxPos[1] = longest[j+1][1];
        				    max = surMax;
        			    }
        			}
        		}
        	}
        	longest[i][0] = maxPos[0];
        	longest[i][1] = maxPos[1];
        }
        
        return longest[0][1] - longest[0][0] + 1;
    }
    
    public static void main(String[] args){
//    	String s = "ghykqfyvlstqrqwnfejvyeaieegsempntnxmvxjgrcwkuuzazivznxhtbpmicdxxrwjo";
//    	String s = "abcabcdd";
//    	String s = "abcabcbb";
    	String s = "eodktnkpcpgvoldawkfamcmigxmcrwswmwihluwnjeixslzoxhojjdtrcftudnsrjczwxxjgc";
    	
    	System.out.println(new Solution().lengthOfLongestSubstring(s));
    }
    
    
}
