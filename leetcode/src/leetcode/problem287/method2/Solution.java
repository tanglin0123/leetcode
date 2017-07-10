package leetcode.problem287.method2;

public class Solution {
	public int findDuplicate(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		
		int n = nums.length;
		
		int curIndex = 0;
		int value = nums[curIndex];
		nums[curIndex] = 0;
		int toPutIndex = value - 1;
		
		while(true){
		    int nextValue = nums[toPutIndex];
			if(value == nextValue){
				return value;
			}
		    
		    nums[toPutIndex] = value;
				
			value = nextValue;
				
			if(value == 0){
			    curIndex = (toPutIndex + 1) % n; 
	            value = nums[curIndex];
			    while(curIndex == value -1){
				    value = nums[++curIndex];
				}
				nums[curIndex] = 0;
			} else {
			    curIndex = toPutIndex;
			    toPutIndex = value -1;
			}
			
		}
	}
}