package leetcode.problem279.method2;

// exceed mem limitation
public class Solution {
	public int numSquares(int n) {
        if(n <= 0){
            return 0;
        }
        
        int[] r = new int[n+1];
        r[0] = 0;
        r[1] = 1;
        
        for(int i = 2; i <= n ; ++i){
            int min = i;
            for(int j = 1; j*j <= i; ++j){
                int k = i-j*j;
                min = Math.min(min, 1+r[k]);
            }
            r[i]=min;
        }
        
        return r[n];
    }


	public static void main(String[] args) {
		Solution s = new Solution();
		long t1 = System.currentTimeMillis();
		int r = s.numSquares(1535);
		long t2 = System.currentTimeMillis();
		System.out.println(r+": " + (t2-t1) + "ms");

	}

}
