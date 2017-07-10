package leetcode.problem397.method1;

public class Solution {
    public long integerReplacementLong(long n) {
        
        if(n==1){
            return 0;
        }
        
        if(n % 2 == 0){
            return 1+ integerReplacementLong(n/2);
        }
        
        long plus = integerReplacementLong(1+n);
        long minus = integerReplacementLong(n-1);
        
        return 1+Math.max(plus, minus);
        
    }
    
    public long integerReplacement(long n) {
        
        return (int)integerReplacementLong(n); 
        
    }

	public static void main(String[] args) {
		int n = Integer.MAX_VALUE;
		
		Solution s = new Solution();
		
		System.out.println(s.integerReplacement(n));

	}

}
