package leetcode.problem502.method1;

import java.util.*;

// back trace - timeout
public class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        int[] max = new int[]{W};
        Set<Integer> select = new HashSet<Integer>();
        
        findMaxCap(k, W, Profits, Capital, max, select);
        
        return max[0];
    }
    
    int findMaxCap(int k, int w, int[] pro, int[] cap, int[] max, Set<Integer> select){
        if(k <= 0){
            return 0;
        }
        
        int curMax = w;
        for(int i = 0; i< cap.length; ++i){
            if(select.contains(i)){
                continue;
            }
            if(w<cap[i]){
                continue;
            }
            
            select.add(i);
            --k;
            int tmpw = w+pro[i];
                        
            if(k>0){
                tmpw=findMaxCap(k, tmpw, pro, cap, max, select);
            }
            
            if(curMax < tmpw){
                curMax = tmpw;
            }
            
            ++k;
            select.remove(i);
        }
        
        if(max[0] < curMax){
            max[0] = curMax;
        }
        
        return curMax;
    }


	public static void main(String[] args) {
		int k = 2;
		int w = 0;
		int[] pro = new int[]{1,2,3};
		int[] cap = new int[]{0,1,1};
		
		Solution s = new Solution();
		System.out.println(s.findMaximizedCapital(k, w, pro, cap));

	}

}
