package leetcode.problem464.method1;

public class Solution {
    
    final int[] masks = {1, 1<<1, 1<<2, 1<<3, 1<<4, 1<<5, 1<<6, 1<<7, 1<<8, 1<<9, 1<<10, 1<<11, 1<<12, 1<<13, 1<<14, 1<<15, 1<<16, 1<<17, 1<<18, 1<<19, 1<<20};
    
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal <= maxChoosableInteger){
            return true;
        }
        
        int maxTotal = (1+maxChoosableInteger)*maxChoosableInteger/2;
        if(maxTotal < desiredTotal){
            return false;
        }
        
        int dpMax = (1 << (maxChoosableInteger + 1)) - 2; // the rightest bit is not used
        
        int[] dp = new int[dpMax + 1]; // 1 - succ, -1 - fail, 0 - not check
        int[] sum = new int[dpMax + 1];
        int[] maxAvl = new int[dpMax + 1];
        
        sum[0] = 0;
        maxAvl[0] = maxChoosableInteger;
        
        return canIWin(0, dp, sum, maxAvl, desiredTotal, sum[0], maxAvl[0]);
    }


    boolean canIWin(int bitMap, int[] dp, int[] sum, int[] maxAvl, int desire, int curSum, int maxAvail){
        if(dp[bitMap] != 0){
            return dp[bitMap] > 0;
        }
        
        if(curSum + maxAvail >= desire){
            dp[bitMap] = 1;
            return true;
        }
        
        boolean succ = false;
        
        for(int i = maxAvail; i >= 1; --i ){
            if((bitMap & masks[i]) == 0) { // i is available
                
                int newBitMap = bitMap | 1<<i;
                
                if(i != maxAvail){
                    maxAvl[newBitMap] = maxAvail;
                } else {
                    maxAvl[newBitMap] = calculateMaxAvail(newBitMap, maxAvail);
                }
                
                sum[newBitMap] = curSum + i;
                
                succ |= !canIWin(newBitMap, dp, sum, maxAvl, desire, sum[newBitMap], maxAvl[newBitMap]);
                if(succ){
                    dp[bitMap] = 1;
                    return true;
                }
            }
        }
        
        dp[bitMap] = -1;
        return false;
    }
    
    int calculateMaxAvail(int bitMap, int preMaxAvail){
        for(int i = preMaxAvail - 1; i >= 1; --i){
            if((bitMap & masks[i]) == 0){
                return i;
            }
        }
        
        return 0;
    }
    
	public static void main(String[] args) {
		int m = 3;
		int d = 5;
		
		Solution s = new Solution();
		System.out.println(s.canIWin(m, d));

	}

}
