package leetcode.problem377.method2;

// time limit exceed
public class Solution {

	public int combinationSum4(int[] nums, int target) {
		int sum = 0;
		for(int i = 0; i < nums.length; ++i){
			int remain = target - nums[i];
			if(remain > 0){
				sum += this.combinationSum4(nums, remain );
			} else if(remain == 0){
				++sum;
			} // else remain < 0
		}
		
		return sum;
	}
	


	public static void main(String[] args) {
//		int[] nums = new int[]{1,2,3};
//		int target = 32;

		int[] nums = new int[]{1,2,3};
		int target = 4;
		
		Solution s = new Solution();
		System.out.println(s.combinationSum4(nums, target));

	}

}
