package leetcode.problem264.method1;

import java.util.*;

// pass, but slow
class Solution {
    static final int[] step = {2,3,5};
    
    public int nthUglyNumber(int n) {
        ArrayList<Long> candidates = new ArrayList<Long>();
        candidates.add(1L);
        
        ArrayList<Integer> multi = new ArrayList<Integer>();
        multi.add(0);
        
        for(int i = 1; i < n; ++i){
            int minIdx = 0;
            long min = Long.MAX_VALUE;
            
            for(int j = 0; j < candidates.size(); ++j){
                long c = candidates.get(j);
                long m = step[multi.get(j)];
                
                if(c*m < min ){
                    min = c*m;
                    minIdx = j;
                }
            }
            
            if(candidates.get(candidates.size()-1) != min){
                candidates.add(min);
                multi.add(0);
            } else {
                --i;
            }
            
            if(multi.get(minIdx) == 2){
                candidates.remove(0);
                multi.remove(0);
            }else{
                multi.set(minIdx, multi.get(minIdx) + 1);
            }
        }
        
        return (int)(candidates.get(candidates.size()-1).longValue());
    }

	public static void main(String[] args) {
		int n = 7;
		
		Solution s = new Solution();
		
		System.out.println(s.nthUglyNumber(n));

	}

}
