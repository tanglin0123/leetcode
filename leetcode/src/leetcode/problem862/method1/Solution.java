package leetcode.problem862.method1;

// time limit exceeded
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int len = A.length;
        
        long[] sum = new long[len+1];
        sum[0] = 0;
        
        for(int i = 1; i <= len; ++i){
            sum[i] = sum[i-1] + A[i-1];
        }
        
        int minlen = Integer.MAX_VALUE;
        for(int i = 1; i <= len; ++i){
            for(int j = i-1; j >= 0; --j){
                if(sum[i] - sum[j] >= K){
                    minlen = Math.min(i - j, minlen);
                    break;
                }
            }
        }
        
        return minlen == Integer.MAX_VALUE ? -1 : minlen;
    }

	public static void main(String[] args) {
	}

}
