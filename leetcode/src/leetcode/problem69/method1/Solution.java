package leetcode.problem69.method1;

public class Solution {
	public int mySqrt(int x) {
		if (x == 0)
			return 0;
		if (x == 1) {
			return 1;
		}
		int small = 1; 
		int big = x / 2;
		while (true) {
			int mid = (small + big) / 2;
			if (mid > x/mid) { // not use * to avoid overflow
				big = mid - 1;
			} else { //mid*mid<=x
				if ((mid + 1) > x/(mid+1)) {
					return mid;
				}
				small = mid + 1;
			}
		}
	}
	
	public static void main(String[] args){
		Solution s = new Solution();
		System.out.println(s.mySqrt(2147395599));
	}
}
