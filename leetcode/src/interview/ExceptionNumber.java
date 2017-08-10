package interview;

// learn from leetcode p137 
// https://discuss.leetcode.com/topic/22821/an-general-way-to-handle-all-this-sort-of-questions
public class ExceptionNumber {
	// given a array of int, every one is occurs m times, except one(not told occur time), 
	// find out the exception one 
	public int findException(int[] nums, int m) {
		int len = (int)Math.ceil((Math.log(m)/Math.log(2)));
		int exNum = nums.length % m;
		
		int[] b = new int[len+1];
		for(int n : nums) {
			int[] newb = new int[len+1];
			
			// calculate newb
			// TODO too hard
			
			b = newb;
		}
		
		int i = (int)(Math.log(exNum)/Math.log(2));
		return b[i+1];
	}
	
	// given a array of int, every one is occurs 3 times, except one which occur times is not told, 
	// find out the exception one 
	public int findException_m3(int[] nums) {
		int b1 = 0, b2 = 0;
		int exNum = nums.length % 3;
		
		for(int n : nums) {
			int newb1 = (b1 & ~b2 & ~n) | (~b1 & b2 & n);
			int newb2 = (~b1 & b2 & ~n) | (~b1 & ~b2 & n);
			
			b1 = newb1;
			b2 = newb2;
		}
		
		if(exNum == 1) {
			return b2;
		} else { // exNum ==2
			return b1;
		}
	}
	
	public static void main(String[] args) {
		int[] a = {3,1,1,2,3,1,3};
//		int[] a = {3,2,1,1,2,3,1,2};
		
		ExceptionNumber e = new ExceptionNumber();
		System.out.println(e.findException_m3(a));
		
	}

}
