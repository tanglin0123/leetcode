package leetcode.problem372.method1;


public class Solution {
    public int superPow(int a, int[] b) {
        int length = b.length;
        
        int r = (int)Math.pow(a, b[length-1]) % 1337;
        int by10 = 10;
        for(int i = length-2; i >=0; --i){
            int r1 = (int)Math.pow(a, b[i] * by10) % 1337;
            r = (r1 * r) % 1337;
            by10*=10;
        }   
        
        return r;
    }
    
    public static void main(String[] args){
    	
    	int a = 2147483647;
    	int[] b = new int[]{2,0,0};
    	
    	Solution s = new Solution();
    	System.out.println(s.superPow(a, b));
    }
}
