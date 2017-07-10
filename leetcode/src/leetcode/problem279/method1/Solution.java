package leetcode.problem279.method1;

import java.util.*;

// exceed mem limitation
public class Solution {
    public int numSquares(int n) {
        if(n <= 0){
            return 0;
        }
        
        List<int []> q = new LinkedList<int []>();
        q.add(new int[]{0, n});
        while(!q.isEmpty()){
            int[] tmp = q.remove(0);
            int sqrt = (int)Math.sqrt(tmp[1]);
            for(int i = sqrt; i >=1; --i){
                int m = i*i;
                int r = tmp[1] -m;
                int t = tmp[0] + 1;
                if(r==0){
                    return t;
                }
                
                q.add(new int[]{t, r});
            }
        }
        
        return n;
    }


	public static void main(String[] args) {
		Solution s = new Solution();
		
		long t1 = System.currentTimeMillis();
		int r = s.numSquares(1535);
		long t2 = System.currentTimeMillis();
		System.out.println(r+": " + (t2-t1) + "ms");

	}

}
