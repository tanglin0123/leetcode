package leetcode.problem3.method1;

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
        
        return findLongest(chs, 0, longest, 0);
    }
    
    private int findLongest(char[] chs, int start, int[][] longest, int maxTillNow){
        if(maxTillNow >= chs.length - start){
            return -1; // means no need
        }
        
        if(start != 0 && longest[start][1] != 0){ // already calculated
            return longest[start][1] - longest[start][0] + 1;
        }
        
        if(start == chs.length -1){
            longest[start][0] = start;
            longest[start][1] = start;
            return 1;
        }
        
        
        boolean[] visited = new boolean[65535];
        
        for(int i = start; i < chs.length; ++i){
            char c = chs[i];
            
            if(visited[(int)c]){
                int curMax = i - start;
                if(curMax > maxTillNow){
                    longest[start][0] = start;
                    longest[start][1] = i-1;
                    maxTillNow = curMax;
                }
                
                int surMax = findLongest(chs, i, longest, maxTillNow);
                
                if(surMax > maxTillNow){
                    longest[start][0] = longest[i][0];
                    longest[start][1] = longest[i][1];
                    maxTillNow = surMax;
                }
                break;
            } else {
                visited[(int)c]=true;
                
                int curMax = i - start + 1;
                if(curMax > maxTillNow){
                    longest[start][0] = start;
                    longest[start][1] = i;
                    maxTillNow = curMax;
                }
                
                int surMax = findLongest(chs, i+1, longest, maxTillNow);
                
                if(surMax > maxTillNow) {
                    longest[start][0] = longest[i+1][0];
                    longest[start][1] = longest[i+1][1];
                    maxTillNow = surMax;
                }
            }
        }
        
        return longest[start][1] - longest[start][0] + 1;
        
    }
    
    
    public static void main(String[] args){
    	String s = "ghykqfyvlstqrqwnfejvyeaieegsempntnxmvxjgrcwkuuzazivznxhtbpmicdxxrwjo";
//    	String s = "abcabcdd";
    	
    	System.out.println(new Solution().lengthOfLongestSubstring(s));
    }
}