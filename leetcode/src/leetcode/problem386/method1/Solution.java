package leetcode.problem386.method1;

import java.util.*;

public class Solution {
    public List<Integer> lexicalOrder(int n) {
        
        List<Integer> result = new ArrayList<Integer>(n);
        
        if(n<1){
            return result;
        }
        
        int prefix = 0;
        backtrace(prefix, result, n);
        
        return result;
    }
    
    void backtrace(int prefix, List<Integer> result, int n){
        for(int i = 0; i <=9; ++i){
            int now = prefix*10+i;
            if(now<n){
            	if(now != 0){
            		result.add(now);
                    backtrace(now, result, n);
            	}

            } else if (now == n ) {
                result.add(now);
                return;
            } else { // now > n
                return;
            }
        }
    }


	public static void main(String[] args) {
		int n = 13;
		
		Solution s = new Solution();
		
		System.out.println(s.lexicalOrder(n));

	}

}
