package leetcode.problem169.method1;

public class Solution {
	public int majorityElement(int[] nums) {
	    int count=0, ret = 0;
	    for (int num: nums) {
	        if (count==0)
	            ret = num;
	        if (num!=ret)
	            count--;
	        else
	            count++;
	    }
	    return ret;
	}
	
	
	public static void main(String[] args) {
		int[] nums = new int[]{2,1,3,1};
		
		System.out.println(new Solution().majorityElement(nums));
	}

}
