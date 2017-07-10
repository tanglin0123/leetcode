package leetcode.problem483.method1;

public class Solution {
	
	public String smallestGoodBase(String n) {
		long num = Long.parseLong(n);
		
		int start = 1;
		for(; start < 60; ++start){
			if(((long)1)<<start > num ){
				break;
			}
		}
		
		for(int m = start-1; m >= 1; --m){
			long base = this.verify(m, num);
			if(base != -1){
				return ""+base;
			}
		}
		
		return ""+(num-1);
    }
	
	private long verify(long m, long num){
		long upper = (long)Math.pow((double)num, 1.0D/(double)m)+1;
		long lower = upper -1;
		
		for(long base = lower; base <= upper; ++base){
			if(powSum(base, m) == num){
				return base;
			}
		}
		
		return -1L;
	}
	
	private long powSum(long base, long pow){
		long sum = 1;
		long prod = 1; 
		for(int i = 1; i <= pow; ++i){
			prod*=base;
			sum+=prod;
		}
		
		return sum;
	}
	
	
	public static void main(String[] arg){
		String num = "13";
		
		Solution s = new Solution();
		
		System.out.println(s.smallestGoodBase(num));
	}
}
