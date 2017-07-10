package leetcode.problem402.method1;

public class Solution {
    public String removeKdigits(String num, int k) {
        if(k == num.length()){
            return "0";
        }
        
        if(k == 0){
            return num;
        }
        
        char[] res = num.toCharArray();
        
        int start = 0;
        int end = res.length -1;
        
        for(int i = 0; i < k; ++i){
            for(int idx = start; idx <= end; ++idx ){
                if(res[idx] == '#'){
                    continue;
                }
                
                int nextIdx = nextIdx(idx, res, end);
                
                if(idx == end || nextIdx == -1 || res[idx] > res[nextIdx]){
                    res[idx] = '#';
                    if(idx == start){
                        start = idx+1;
                    } else if(idx == end){
                        end = idx-1;
                    }
                    break;
                }
            }
        }
        
        String r = "";
        for(char c : res){
            if(c=='#'){
                continue;
            }
            r += c;
        }
        
        return r;
    }
    
    int nextIdx(int idx, char[] res, int end){
    	for(int i = idx + 1; i <= end; ++i){
    		if(res[i] == '#'){
    			continue;
    		}
    		return i;
    	}
    	return -1;
    }
    
    public static void main(String args[]){
    	String a ="1432219";
    	int b = 3;
    	
    	Solution s = new Solution();
    	System.out.println(s.removeKdigits(a, b));
    }
}